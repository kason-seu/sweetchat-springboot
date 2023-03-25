package com.chat.pojo.wx;

import lombok.Data;

/**
 * <xml>
 *  <ToUserName><![CDATA[公众号]]></ToUserName>
 *  <FromUserName><![CDATA[粉丝号]]></FromUserName>
 *  <CreateTime>1460537339</CreateTime>
 *  <MsgType><![CDATA[text]]></MsgType>
 *  <Content><![CDATA[欢迎开启公众号开发者模式]]></Content>
 * </xml>
 */

@Data
public class TextMessageRes {

    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Content;


}
