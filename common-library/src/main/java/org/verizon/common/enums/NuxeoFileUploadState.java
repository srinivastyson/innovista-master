/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.enums;

/**
 * Response Code for server responses.
 * 
 * @author abhishek
 * @since  1.0
 */
public enum NuxeoFileUploadState {
    SCHEDULED(1),
    UPLOADED(2),
    FAILED(3);

    private int value;

    private NuxeoFileUploadState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
