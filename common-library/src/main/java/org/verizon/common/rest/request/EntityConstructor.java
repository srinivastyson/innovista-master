/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.rest.request;

import org.apache.http.HttpEntity;

/**
 * @author abhishek
 * @since  1.0
 */
public interface EntityConstructor {

    HttpEntity constructEntity() throws Exception;

}
