/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.enums;

/**
 * Response Code for server responses.
 * 
 * @author abhishek
 * @since  1.0
 */
public enum ResponseCode {
    WARN("warn"),
    INFO("info"),
    SUCCESS("done"),
    FAILURE("fail");

    private String value;

    private ResponseCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
