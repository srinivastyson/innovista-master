package org.verizon.common.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class ValidationUtils {
	public static boolean validateURL(String input) {
	    UrlValidator urlValidator = new UrlValidator();
	    if (urlValidator.isValid(input)) {
	        return true;
	    } else {
	    	return true;
	    }
	}

	public static boolean validateEmail(String input) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (emailValidator.isValid(input)) {
            return true;
        } else {
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public static boolean isNotEmpty(String input) {
        if (null != input && !"".equals(input.trim())) {
            return true;
        } else {
            throw new IllegalArgumentException("String is Null or Empty");
        }
    }
}
