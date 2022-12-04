package com.chat.controler;

import com.chat.enums.OperatorFriendRequestTypeEnum;
import com.chat.enums.SearchFriendsStatusEnum;
import com.chat.pojo.Users;
import com.chat.pojo.bo.UsersBO;
import com.chat.pojo.vo.MyFriendsVO;
import com.chat.pojo.vo.UsersVO;
import com.chat.service.UserService;
import com.chat.utils.ApiJSONResponse;
import com.chat.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserControler {

    @Autowired
    private UserService userService;

    /**
     * @Description: 用户注册/登录
     */
    @PostMapping("/registOrLogin")
    public ApiJSONResponse registOrLogin(@RequestBody Users user) throws Exception {

        // 0. 判断用户名和密码不能为空
        if (StringUtils.isBlank(user.getUsername())
                || StringUtils.isBlank(user.getPassword())) {
            return ApiJSONResponse.errorMsg("用户名或密码不能为空...");
        }

        // 1. 判断用户名是否存在，如果存在就登录，如果不存在则注册
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        Users userResult = null;
        if (usernameIsExist) {
            // 1.1 登录
            userResult = userService.queryUserForLogin(user.getUsername(),
                    MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return ApiJSONResponse.errorMsg("用户名或密码不正确...");
            }
        } else {
            // 1.2 注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userResult = userService.saveUser(user);
        }

        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(userResult, userVO);

        return ApiJSONResponse.ok(userVO);
    }


    /**
     * @Description: 设置用户昵称
     */
    @PostMapping("/setNickname")
    public ApiJSONResponse setNickname(@RequestBody UsersBO userBO) throws Exception {

        log.info("receive web request params {}", userBO);
        Users user = new Users();
        user.setId(userBO.getUserId());
        user.setNickname(userBO.getNickname());
        Users result = userService.updateUserInfo(user);


        return ApiJSONResponse.ok(result);
    }


    /**
     * @Description: 搜索好友接口, 根据账号做匹配查询而不是模糊查询
     */
    @PostMapping("/search")
    public ApiJSONResponse searchUser(String myUserId, String friendUsername)
            throws Exception {

        // 0. 判断 myUserId friendUsername 不能为空
        if (StringUtils.isBlank(myUserId)
                || StringUtils.isBlank(friendUsername)) {
            return ApiJSONResponse.errorMsg("");
        }

        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
        if (status == SearchFriendsStatusEnum.SUCCESS.status) {
            Users user = userService.queryUserInfoByUsername(friendUsername);
            UsersVO userVO = new UsersVO();
            BeanUtils.copyProperties(user, userVO);
            return ApiJSONResponse.ok(userVO);
        } else {
            String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
            return ApiJSONResponse.errorMsg(errorMsg);
        }
    }


    /**
     * @Description: 发送添加好友的请求
     */
    @PostMapping("/addFriendRequest")
    public ApiJSONResponse addFriendRequest(String myUserId, String friendUsername)
            throws Exception {

        // 0. 判断 myUserId friendUsername 不能为空
        if (StringUtils.isBlank(myUserId)
                || StringUtils.isBlank(friendUsername)) {
            return ApiJSONResponse.errorMsg("");
        }

        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
        if (status == SearchFriendsStatusEnum.SUCCESS.status) {
            userService.sendFriendRequest(myUserId, friendUsername);
        } else {
            String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
            return ApiJSONResponse.errorMsg(errorMsg);
        }

        return ApiJSONResponse.ok();
    }


    /**
     * @Description: 发送添加好友的请求
     */
    @PostMapping("/queryFriendRequests")
    public ApiJSONResponse queryFriendRequests(String userId) {

        // 0. 判断不能为空
        if (StringUtils.isBlank(userId)) {
            return ApiJSONResponse.errorMsg("");
        }

        // 1. 查询用户接受到的朋友申请
        return ApiJSONResponse.ok(userService.queryFriendRequestList(userId));
    }

    /**
     * @Description: 接受方 通过或者忽略朋友请求
     */
    @PostMapping("/operateFriendRequest")
    public ApiJSONResponse operateFriendRequest(String acceptUserId, String sendUserId,
                                             @RequestParam("operType") Integer operateType) {

        // 0. acceptUserId sendUserId operType 判断不能为空
        if (StringUtils.isBlank(acceptUserId)
                || StringUtils.isBlank(sendUserId)
                || operateType == null) {
            return ApiJSONResponse.errorMsg("");
        }

        // 1. 如果operType 没有对应的枚举值，则直接抛出空错误信息
        if (StringUtils.isBlank(OperatorFriendRequestTypeEnum.getMsgByType(operateType))) {
            return ApiJSONResponse.errorMsg("");
        }

        if (operateType == OperatorFriendRequestTypeEnum.IGNORE.type) {
            // 2. 判断如果忽略好友请求，则直接删除好友请求的数据库表记录
            userService.deleteFriendRequest(sendUserId, acceptUserId);
        } else if (operateType == OperatorFriendRequestTypeEnum.PASS.type) {
            // 3. 判断如果是通过好友请求，则互相增加好友记录到数据库对应的表
            //	   然后删除好友请求的数据库表记录
            userService.passFriendRequest(sendUserId, acceptUserId);
        }

        // 4. 数据库查询好友列表
        List<MyFriendsVO> myFirends = userService.queryMyFriends(acceptUserId);

        return ApiJSONResponse.ok(myFirends);
    }


    /**
     * @Description: 查询我的好友列表
     */
    @PostMapping("/myFriends")
    public ApiJSONResponse myFriends(String userId) {
        // 0. userId 判断不能为空
        if (StringUtils.isBlank(userId)) {
            return ApiJSONResponse.errorMsg("");
        }

        // 1. 数据库查询好友列表
        List<MyFriendsVO> myFirends = userService.queryMyFriends(userId);

        return ApiJSONResponse.ok(myFirends);
    }


}
