package com.chat.listener;

import space.chensheng.wechatty.common.message.MessageListener;
import space.chensheng.wechatty.common.message.MessageType;
import space.chensheng.wechatty.common.message.base.ReplyMessage;
import space.chensheng.wechatty.mp.message.inbound.event.SubscribeEventMessage;

public class SubscribeEventListener extends MessageListener<SubscribeEventMessage> {

    @Override
    protected ReplyMessage onMessage(SubscribeEventMessage message) {
        String fromUser = message.getFromUserName();
        String toUser = message.getToUserName();
        MessageType msgType = message.getMsgType();


        //返回null表示不回复用户
        return null;
    }
}
