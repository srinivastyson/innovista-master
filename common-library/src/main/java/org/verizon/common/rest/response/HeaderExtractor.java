/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.rest.response;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

/**
 * @author abhishek
 * @since  1.0
 */
public class HeaderExtractor implements Extractor {

    public String extractResponse(HttpResponse response) throws Exception {
        // Extract JSON
        Header[] headers = response.getAllHeaders();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < headers.length; i++) {
            result.append(headers[i].getName()).append(':').append(headers[i].getValue()).append("||");
        }
        return result.toString();
    }

}
