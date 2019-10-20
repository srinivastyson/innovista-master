/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.enums;

/**
 * Response Code for server responses.
 * 
 * @author abhishek
 * @since  1.0
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    private String value;

    private HttpMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
