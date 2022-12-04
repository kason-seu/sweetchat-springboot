package org.example.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * channel注册后会执行里面的响应的初始化放啊
 */
public class HelloServerInitializer  extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        // 通过SocketChannel去获取对应的管道
        ChannelPipeline pipeline = channel.pipeline();
        // 通过管道添加handler
        // HttpServerCodec 可以理解为拦截器
        // 当请求到服务端，我们需要做解码，响应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        //添加自定义的handlerl类，返回hello netty
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
