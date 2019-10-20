/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.rest;

import java.io.IOException;

import org.verizon.common.rest.response.DefaultJsonExtractor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * TODO
 * 
 * @author abhishek
 * @since 1.0
 */
public class MainTester {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
    	String json = (String) new HttpRestBuilder.Builder("http://jsonplaceholder.typicode.com/posts/1")
                .get()
                .header("Content-Type", "application/json")
                .responseExtractor(new DefaultJsonExtractor()).build();
    	System.out.println("json : " + json);
    }
}
