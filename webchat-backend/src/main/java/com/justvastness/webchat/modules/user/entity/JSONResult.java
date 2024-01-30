package com.justvastness.webchat.modules.user.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JSONResult {
    private boolean ok;
    private String reason;
    private Object data;
}
