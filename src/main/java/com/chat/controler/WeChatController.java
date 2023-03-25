package com.chat.controler;

import com.chat.service.api.WeChatApi;
import com.chat.utils.wx.EventDispatcher;
import com.chat.utils.wx.MessageUtil;
import com.chat.utils.wx.MsgDispatcher;
import com.chat.utils.wx.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@Slf4j
public class WeChatController implements WeChatApi {
    /**
     *
     * @param request
     * @return void
     **/
//    @Override
//    public String checkToken(HttpServletRequest request) {
//        String signature = request.getParameter("signature");
//        String timestamp = request.getParameter("timestamp");
//        String nonce = request.getParameter("nonce");
//        String echostr = request.getParameter("echostr");
//        log.info("signature[{}], timestamp[{}], nonce[{}], echostr[{}]", signature, timestamp, nonce, echostr);
//        if(SignUtil.checkSignature(signature, timestamp, nonce)){
//            log.info("数据源为微信后台，将echostr[{}]返回！", echostr);
//            return echostr;
//        }
//        return "";
//    }

    /**
     * @author: yh
     * @description: token验证
     * @date: 2020/8/20
     * @param request
     * @return void
     **/
    @Override
    public String doGet(HttpServletRequest request,HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info("signature[{}], timestamp[{}], nonce[{}], echostr[{}]", signature, timestamp, nonce, echostr);
        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            log.info("数据源为微信后台，将echostr[{}]返回！", echostr);
            return echostr;
        }
        return "";
    }

    /**
     * @author: yh
     * @description: 消息/事件的接收和处理
     * @date: 2020/8/21
     * @param request
     * @param response
     * @return void
     **/
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> messageMap = MessageUtil.parseXml(request);
            log.info("消息：{}", messageMap.get("Content"));
            String type = messageMap.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(type)){
                //事件处理
                EventDispatcher.processEvent(messageMap);
            }else {
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                //消息处理
                String data = MsgDispatcher.processMessage(messageMap);
                PrintWriter out = response.getWriter();
                out.print(data);
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
