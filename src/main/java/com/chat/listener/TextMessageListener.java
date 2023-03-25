package com.chat.listener;

import space.chensheng.wechatty.common.message.MessageListener;
import space.chensheng.wechatty.common.message.base.ReplyMessage;
import space.chensheng.wechatty.mp.message.inbound.simple.TextInboundMessage;
import space.chensheng.wechatty.mp.message.reply.TextReplyMessage;

public class TextMessageListener extends MessageListener<TextInboundMessage> {

    @Override
    protected ReplyMessage onMessage(TextInboundMessage message) {
        String content = message.getContent();

        //根据消息内容来回复用户
        if ("1".equals(content)) {
            TextReplyMessage replyMsg = new TextReplyMessage();
            replyMsg.setContent("this is reply message content");
            replyMsg.setFromUserName(message.getToUserName());
            replyMsg.setToUserName(message.getFromUserName());
            replyMsg.setCreateTime(System.currentTimeMillis());
            return replyMsg;
        }

        //返回null表示不回复用户
        return null;
    }
}
