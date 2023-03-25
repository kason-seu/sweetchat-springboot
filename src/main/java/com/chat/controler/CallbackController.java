package com.chat.controler;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import space.chensheng.wechatty.mp.util.MpAppContext;
//
//@RestController
//@RequestMapping(value = "/wechat-mp")
//public class CallbackController {
//
//    @Autowired
//    private MpAppContext mpAppContext;
//
//    //验证请求，并回复字符串
//    @RequestMapping(value = "/callback", method = RequestMethod.GET)
//    public String verify(String msg_signature, String timestamp, String nonce, String echostr) {
//        String reply = mpAppContext.getCallbackModeVerifier().verify(msg_signature, timestamp, nonce, echostr);
//        return reply;
//    }
//
//    //接收回调消息，并回复相应xml消息
//    @RequestMapping(value = "/callback", method = RequestMethod.POST)
//    public String verify(String msg_signature, String timestamp, String nonce) {
//        //postBody是请求体内容，String格式，开发者可以通过HttpServletRequest来解析
//        String replyXml = mpAppContext.getMpMessageDispatcher().dispatch(msg_signature(), timestamp, nonce, postBody);
//        return replyXml;
//    }
//
//}
//
