package com.chat.pojo.wx;

import lombok.Data;

/**
 * @description: 语音消息
 */
@Data
public class VoiceMessage extends BaseMessage {
    // 语音消息媒体id，可以调用获取临时素材+接口拉取数据。
    private String MediaId;
    // 语音格式
    private String Format;
}
