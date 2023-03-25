package com.chat.service.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 */
@RequestMapping("/WeChatApi")
public interface WeChatApi {
    /**
     * @author: yh
     * @description: token验证
     * @date: 2020/8/20
     * @param request
     * @return void
     **/
//    @GetMapping("/checkToken")
//    String checkToken(HttpServletRequest request);

    /**
     * @description: token验
     * @param request
     * @return void
     **/
    @GetMapping("/wxRequest")
    String doGet(HttpServletRequest request, HttpServletResponse response);

    /**
     * @description: 消息的接收和处理
     *
     * @param request
     * @param response
     * @return void
     **/
    @PostMapping("/wxRequest")
    void doPost(HttpServletRequest request, HttpServletResponse response);

}

