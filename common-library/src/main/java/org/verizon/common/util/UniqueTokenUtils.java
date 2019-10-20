package org.verizon.common.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class UniqueTokenUtils {
	private static final String CODE = "APP.DMS";

	public static String generateUserToken(String input) {
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword(CODE);
		return textEncryptor.encrypt(input);
	}

	public static boolean validateUserToken(String input, String encrypted) {
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword(CODE);
		if (input.equals(textEncryptor.decrypt(encrypted))) {
			return true;
		}
		return false;
	}
}
