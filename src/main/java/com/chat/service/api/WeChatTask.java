package com.chat.service.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * @description: 定时任务
 */
@Component
@Slf4j
public class WeChatTask {


    @Resource
    private TaskService taskService;


    @PostMapping("/getToken")
    @Scheduled(fixedDelay=90*60*1000)
    public void getToken() throws Exception {
        log.info("定时任务：重新获取token");
        taskService.getToken();
    }

}

