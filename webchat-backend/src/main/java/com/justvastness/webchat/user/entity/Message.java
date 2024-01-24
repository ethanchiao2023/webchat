package com.justvastness.webchat.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Message {
    private Integer id;
    private String userNickname;
    private Integer channelId;
    private String content;
    private Date sendTime;
}
