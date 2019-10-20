/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.rest.request;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author abhishek
 * @since  1.0
 */
public class DefaultJsonConstructor implements EntityConstructor {
    private Object dataobject;

    public DefaultJsonConstructor(Object dataobject) {
        this.dataobject = dataobject;
    }

    /**
     * @author abhishek
     * @since  1.0
     * @see org.verizon.common.rest.request.EntityConstructor#constructEntity(java.lang.Object[])
     */
    @Override
    public HttpEntity constructEntity() throws Exception {
        try {
            return new StringEntity(new ObjectMapper().writeValueAsString(dataobject));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
