/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.sharingservices.enums;

/**
 * @author Bhuvitha
 * @since  1.0
 */
public enum EmailSendState {
    SUCCESS(1),
    FAILURE(2),
    QUEUED(10);

    private int value;

    private EmailSendState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
