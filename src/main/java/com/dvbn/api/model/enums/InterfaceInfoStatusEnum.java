package com.dvbn.api.model.enums;

import lombok.Getter;

/**
 * @author dvbn
 * @title: InterfaceInfoStatusEnum
 * @createDate 2023/11/23 21:38
 */
@Getter
public enum InterfaceInfoStatusEnum {

    ONLINE("关闭", 0),
    OFFLINE("上线", 1);
    private final String text;

    private final int value;

    InterfaceInfoStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

}
