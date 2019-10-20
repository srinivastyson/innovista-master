/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.common.rest.execute;

import org.verizon.common.rest.HttpRestBuilder;

/**
 * TODO
 * @author abhishek
 * @since  1.0
 */
public interface InterceptExecute {

    void process(HttpRestBuilder restUtil) throws Exception;

}
