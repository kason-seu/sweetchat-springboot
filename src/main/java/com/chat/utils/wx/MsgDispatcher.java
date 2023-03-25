package com.chat.utils.wx;






import com.alibaba.fastjson.JSONObject;
import com.chat.pojo.wx.TextMessageReq;
import com.chat.pojo.wx.TextMessageRes;

import java.util.Date;
import java.util.Map;

/**
 * @description: 微信消息业务处理分发器
 */
public class MsgDispatcher {
    public static String processMessage(Map<String, String> map) {
        String openid=map.get("FromUserName"); //用户openid
        String mpid=map.get("ToUserName");   //公众号原始ID
        TextMessageRes textMessageRes = new TextMessageRes();
        textMessageRes.setToUserName(openid);
        textMessageRes.setFromUserName(mpid);
        textMessageRes.setCreateTime(new Date().getTime());
        textMessageRes.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            System.out.println("==============这是文本消息！");
            String jsonString = JSONObject.toJSONString(map);
            System.out.println("==============" + jsonString);
            TextMessageReq textMessage = JSONObject.parseObject(jsonString, TextMessageReq.class);
            System.out.println("==============textMessage " + textMessage);
            textMessageRes.setContent("你好，这里是测试回复");

            return MessageUtil.textMessageToXml(textMessageRes);
        }

//        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
//            System.out.println("==============这是图片消息！");
//            String jsonString = JSONObject.toJSONString(map);
//            ImageMessageReq imageMessage = JSONObject.parseObject(jsonString, ImageMessageReq.class);
//
//        }
//
//
//        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
//            System.out.println("==============这是链接消息！");
//            String jsonString = JSONObject.toJSONString(map);
//            LinkMessageReq linkMessage = JSONObject.parseObject(jsonString, LinkMessageReq.class);
//
//        }
//
//
//        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
//            System.out.println("==============这是位置消息！");
//            String jsonString = JSONObject.toJSONString(map);
//            LocationMessageReq locationMessage = JSONObject.parseObject(jsonString, LocationMessageReq.class);
//
//        }
//
//
//        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频/小视频消息
//            System.out.println("==============这是视频消息！");
//            String jsonString = JSONObject.toJSONString(map);
//            VideoMessageReq videoMessage = JSONObject.parseObject(jsonString, VideoMessageReq.class);
//
//        }
//
//        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
//            System.out.println("==============这是语音消息！");
//            String jsonString = JSONObject.toJSONString(map);
//            VoiceMessageReq voiceMessage = JSONObject.parseObject(jsonString, VoiceMessageReq.class);
//
//        }
        return "";
    }
}
