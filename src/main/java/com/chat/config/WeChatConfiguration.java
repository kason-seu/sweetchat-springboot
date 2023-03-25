package com.chat.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yh
 * @date 2020/8/21 17:24
 * @description:
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfiguration {

    private String appid;

    private String AppSecret;

    private String tokenUrl;

}

