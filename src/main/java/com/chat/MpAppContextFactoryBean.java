package com.chat;

import com.chat.listener.SubscribeEventListener;
import com.chat.listener.TextMessageListener;
import com.chat.listener.UnsubscribeEventListener;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import space.chensheng.wechatty.mp.util.MpAppContext;
import space.chensheng.wechatty.mp.util.WechatMpBootstrap;

@Component
public class MpAppContextFactoryBean implements FactoryBean<MpAppContext> {

    @Override
    public MpAppContext getObject() throws Exception {
        WechatMpBootstrap bootstrap = new WechatMpBootstrap();
        bootstrap.addMsgListener(new TextMessageListener());
        bootstrap.addMsgListener(new SubscribeEventListener());
        bootstrap.addMsgListener(new UnsubscribeEventListener());
        return bootstrap.build();
    }


    @Override
    public Class<?> getObjectType() {
        return MpAppContext.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}