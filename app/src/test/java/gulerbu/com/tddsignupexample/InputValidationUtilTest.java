/*
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.tddsignupexample;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class InputValidationUtilTest {

    @Test
    public void checkIfPasswordHasNoDigit() {
        assertFalse(InputValidationUtil.isPasswordValid("fsdfreRR"));
    }

    @Test
    public void checkIfPasswordHasNoUpperCase() {
        assertFalse(InputValidationUtil.isPasswordValid("hello234"));
    }

    @Test
    public void checkIfPasswordHasNoLowerCase() {
        assertFalse(InputValidationUtil.isPasswordValid("234TERF3"));
    }

    @Test
    public void checkIfPasswordLengthIsNotValid() {
        assertFalse(InputValidationUtil.isPasswordValid("12aB"));
    }

    @Test
    public void checkIfPasswordIsValid() {
        assertTrue(InputValidationUtil.isPasswordValid("BurC43e"));
    }

    @Test
    public void checkIfPasswordIsTooLong() {
        assertEquals(false, InputValidationUtil.isPasswordValid("Br45sd2323rsdfsdf"));
    }

    @Test
    public void checkIfPasswordIsTooShort() {
        assertEquals(false, InputValidationUtil.isPasswordValid("Br4"));
    }

    @Test
    public void checkIfUserIsNotValid() {
        assertEquals(false, InputValidationUtil.isUsernameValid(""));
    }
}
