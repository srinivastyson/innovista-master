/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.dataobjects;

import java.io.Serializable;

import org.verizon.common.enums.ResponseCode;

/**
 * Wrapper for all server side responses.
 * 
 * @author abhishek
 * @since 1.0
 */
public class ServerResponse<T> implements Serializable {
    private static final long serialVersionUID = 5276020594508872882L;

    private ResponseCode code;
    private T            response;

    public ServerResponse() {
        super();
    }

    public ServerResponse(ResponseCode code, T response) {
        super();
        this.code = code;
        this.response = response;
    }

    /**
     * @return the code
     * @since 1.0
     * @see ResponseCode
     */
    public ResponseCode getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     * @since 1.0
     * @see ResponseCode
     */
    public void setCode(ResponseCode code) {
        this.code = code;
    }

    /**
     * @return the response
     * @since 1.0
     * @see T
     */
    public T getResponse() {
        return response;
    }

    /**
     * @param response
     *            the response to set
     * @since 1.0
     * @see T
     */
    public void setResponse(T response) {
        this.response = response;
    }
}
