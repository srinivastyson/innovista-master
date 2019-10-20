/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.rest.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

/**
 * @author abhishek
 * @since  1.0
 */
public class DefaultJsonExtractor implements Extractor {

    public String extractResponse(HttpResponse response) throws Exception {
        // Extract JSON
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
