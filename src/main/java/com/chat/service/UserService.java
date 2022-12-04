package com.chat.service;

import com.chat.nety.ChatMsg;
import com.chat.pojo.Users;
import com.chat.pojo.vo.FriendRequestVO;
import com.chat.pojo.vo.MyFriendsVO;

import java.util.List;


public interface UserService {

	/**
	 * @Description: 判断用户名是否存在
	 */
	public boolean queryUsernameIsExist(String username);
	
	/**
	 * @Description: 查询用户是否存在
	 */
	public Users queryUserForLogin(String username, String pwd);
	/**
	 * @Description: 用户注册
	 */
	public Users saveUser(Users user);
	public Users updateUserInfo(Users user);

	/**
	 * 查看是否是好友
	 * @param myUserId
	 * @param friendUsername
	 * @return
	 */
	public Integer preconditionSearchFriends(String myUserId, String friendUsername);

	/**
	 * 根据用户名查看用户信息
	 * @param username
	 * @return
	 */
	public Users queryUserInfoByUsername(String username);

	/**
	 * 好友添加请求
	 * @param myUserId
	 * @param friendUsername
	 */
	public void sendFriendRequest(String myUserId, String friendUsername);

	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	public void deleteFriendRequest(String sendUserId, String acceptUserId);
	public void passFriendRequest(String sendUserId, String acceptUserId);
	public List<MyFriendsVO> queryMyFriends(String userId);


	/**
	 * @Description: 保存聊天消息到数据库
	 */
	public String saveMsg(ChatMsg chatMsg);
	/**
	 * @Description: 批量签收消息
	 */
	public void updateMsgSigned(List<String> msgIdList);

}
