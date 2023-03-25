package com.chat.listener;

import space.chensheng.wechatty.common.message.MessageListener;
import space.chensheng.wechatty.common.message.MessageType;
import space.chensheng.wechatty.common.message.base.ReplyMessage;
import space.chensheng.wechatty.mp.message.inbound.event.UnsubscribeEventMessage;

public class UnsubscribeEventListener extends MessageListener<UnsubscribeEventMessage> {

    @Override
    protected ReplyMessage onMessage(UnsubscribeEventMessage message) {
        String fromUser = message.getFromUserName();
        String toUser = message.getToUserName();
        MessageType msgType = message.getMsgType();

        //返回null表示不回复用户
        return null;
    }
}