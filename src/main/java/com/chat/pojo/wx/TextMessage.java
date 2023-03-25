package com.chat.pojo.wx;
import lombok.Data;

/**
 * @description: 文本消息
 */
@Data
public class TextMessage extends BaseMessage{
    // 消息内容
    private String Content;
}
