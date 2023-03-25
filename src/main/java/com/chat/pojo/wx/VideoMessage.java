package com.chat.pojo.wx;

import lombok.Data;

/**
 * @description:  视频/小视频消息
 */
@Data
public class VideoMessage extends BaseMessage{

    // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
    private String MediaId;
    // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
    private String ThumbMediaId;

}