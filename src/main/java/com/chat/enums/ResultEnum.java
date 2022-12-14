package com.chat.enums;

import lombok.Getter;
/**
 * @Description: 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 */



@Getter
public enum ResultEnum {

    SUCCESS(200, "处理成功"),
    ERROR(500, "错误"),
    BEAN_ERROR(501, "bean验证错误"),
    USER_TOKEN_ERROR(502, "bean验证错误"),
    EXCEPTION(555, "bean验证错误");

    private int status;
    private String message;

    ResultEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
