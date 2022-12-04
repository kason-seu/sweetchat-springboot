package com.chat.utils;

import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JsonUtilsTest {

    @Test
    public void objectToJson() {

        List<String> lists = new ArrayList<>();
        lists.add("aaa");
        String s = JsonUtils.objectToJson(lists);
        System.out.println(s);
    }

    @Test
    public void jsonToPojo() {
    }

    @Test
    public void jsonToList() {
    }
}