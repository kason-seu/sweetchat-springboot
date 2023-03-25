package com.chat.utils.wx;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @description: token验证工具类
 */
public class SignUtil {
    //与服务器保持一致
    private static String token = "HP9VzKN2Su7KUrXS3EJpg";

    /**
     * @author: yh
     * @description: 校验签名
     * @date: 2020/8/20
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return boolean
     **/
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String checkText = null;
        if (null != signature) {
            //对ToKen,timestamp,nonce 按字典排序
            String[] paramArr = new String[]{token,timestamp,nonce};
            Arrays.sort(paramArr);
            //将排序后的结果拼成一个字符串
            String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                //对接后的字符串进行sha1加密
                byte[] digest = md.digest(content.toString().getBytes());

                checkText = byteToStr(digest);
            } catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
        }
        //将加密后的字符串与signature进行对比
        return checkText != null ? checkText.equals(signature.toUpperCase()) : false;
    }

    /**
     * @author: yh
     * @description: 将字节数组转化我16进制字符串
     * @date: 2020/8/20
     * @param byteArrays 字符数组
     * @return java.lang.String
     **/
    private static String byteToStr(byte[] byteArrays){
        String str = "";
        for (int i = 0; i < byteArrays.length; i++) {
            str += byteToHexStr(byteArrays[i]);
        }
        return str;
    }

    /**
     * @author: yh
     * @description: 将字节转化为十六进制字符串
     * @date: 2020/8/20
     * @param myByte 字节
     * @return java.lang.String
     **/
    private static String byteToHexStr(byte myByte) {
        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] tampArr = new char[2];
        tampArr[0] = Digit[(myByte >>> 4) & 0X0F];
        tampArr[1] = Digit[myByte & 0X0F];
        String str = new String(tampArr);
        return str;
    }

}

