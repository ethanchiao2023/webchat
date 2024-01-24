package com.justvastness.webchat.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息内容类型
 * Multipurpose Internet Mail Extensions
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/11/10 2:28 下午
 **/
@Getter
@AllArgsConstructor
public enum MimeEnum {

    /**
     * MIME 类型
     */
    HTML("text/html;charset=UTF-8"),
    PDF("application/pdf"),
    XLS("application/vnd.ms-excel"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    DOC("application/msword"),
    XML("text/xml"),
    RTF("application/rtf"),
    CSV("application/csv;charset=UTF-8"),
    TXT("text/plain;charset=UTF-8"),
    ZIP("application/zip"),
    GZIP("application/gzip"),
    AWF("application/vnd.adobe.workflow"),
    ;

    private final String resContentType;

    public String value() {
        return this.toString();
    }
}
