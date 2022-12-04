package com.chat.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilsTest {

    @Test
    public void getMD5Str() throws Exception {

        String aaa = MD5Utils.getMD5Str("sweet-chat");
        System.out.println(aaa);
    }
}