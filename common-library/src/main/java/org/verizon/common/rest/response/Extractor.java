/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.rest.response;

import org.apache.http.HttpResponse;

/**
 * @author abhishek
 * @since  1.0
 */
public interface Extractor {

    String extractResponse(HttpResponse response) throws Exception;

}
