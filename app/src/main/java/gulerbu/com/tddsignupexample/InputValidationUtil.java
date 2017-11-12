/*
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.tddsignupexample;


import java.util.regex.Pattern;

public final class InputValidationUtil {

    // Requires at least one upper case letter, one lower case letter, one digit
    private final static String PASSWORD_REGEX = "^\\w*(?=\\w*[0-9])(?=\\w*[a-z])(?=\\w*[A-Z])\\w*$";
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 10;

    private InputValidationUtil() {

    }

    public static boolean isPasswordValid(String password) {
        return isPasswordLengthValid(password) && isPasswordValidForRegex(password);

    }

    private static boolean isPasswordLengthValid(String password) {
        return MIN_PASSWORD_LENGTH <= password.length() && password.length() <= MAX_PASSWORD_LENGTH;
    }

    private static boolean isPasswordValidForRegex(String password) {
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }

    public static boolean isUsernameValid(String username) {
        return username != null && !username.isEmpty();
    }
}
