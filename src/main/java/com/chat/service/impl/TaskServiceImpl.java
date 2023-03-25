package com.chat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chat.config.WeChatConfiguration;
import com.chat.service.api.TaskService;
import com.chat.utils.wx.HttpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService
{

    @Resource
    private WeChatConfiguration weChatConfiguration;
    /**
     * @description: 获取微信token
     * @param
     * @return void
     **/
    @Override
    public void getToken() throws Exception {

        Map<String, String> params = new HashMap<String, String>();

        params.put("grant_type", "client_credential");
        params.put("appid", weChatConfiguration.getAppid());
        params.put("secret", weChatConfiguration.getAppSecret());

        String json = HttpUtils.sendGet(weChatConfiguration.getTokenUrl(), params);
        String access_token = JSONObject.parseObject(json).getString("access_token");

        System.out.println(LocalDateTime.now() +"token为=============================="+access_token);
    }
}