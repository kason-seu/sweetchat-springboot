package com.chat.pojo.bo;

import lombok.Data;

@Data
public class UsersBO {
    private String userId;
    private String faceData;
    private String nickname;

    @Override
    public String toString() {
        return "UsersBO{" +
                "userId='" + userId + '\'' +
                ", faceData='" + faceData + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
