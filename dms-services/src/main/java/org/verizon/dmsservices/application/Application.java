/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.dmsservices.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Starting point for this micro-service.
 * 
 * @author abhishek
 * @since 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = { "org.verizon.dmsservices" })
public class Application {
    /**
     * @param args
     * @author abhishek
     * @since 1.0
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
