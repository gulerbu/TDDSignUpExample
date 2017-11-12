/*
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.tddsignupexample;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignUpPresenterTest {

    private static final String VALID_USERNAME = "burcu";
    private static final String VALID_PASSWORD = "aaR34ertG";

    private SignUpPresenter signUpPresenter;

    @Mock
    private SignUpView signUpView;

    @Mock
    private SignUpService signUpService;


    @Before
    public void initPresenter() {
        signUpPresenter = new SignUpPresenter(signUpView, signUpService);
    }

    @Test
    public void checkIfProgressIsShownWhenSignUpClicked() {
        signUpPresenter.signUpClicked("burcu", "dsfsfT4");
        verify(signUpView).showProgress();

    }

    @Test
    public void checkIfErrorShownWhenPasswordIsInvalid() {
        /**
         * Objects can be mocked by mock() method too. These lines are commented out because @Mock is used
         * for object declaration
         */
//        SignUpView signUpView = mock(SignUpView.class);
//        SignUpPresenter signUpPresenter = new SignUpPresenter(signUpView);

        signUpPresenter.signUpClicked("burcu", "aaR");
        verify(signUpView).showErrorMessageForInvalidPasswordOrUserName();
    }

    @Test
    public void checkIfErrorShownWhenUsernameIsInvalid() {
        signUpPresenter.signUpClicked("", "aaR34ertG");
        verify(signUpView).showErrorMessageForInvalidPasswordOrUserName();

    }

    @Test
    public void checkSuccessfulSignUp() {
        ArgumentCaptor<SignUpService.Callback> argumentCaptor
                = ArgumentCaptor.forClass(SignUpService.Callback.class);

        signUpPresenter.signUpClicked(VALID_USERNAME, VALID_PASSWORD);

        verify(signUpView).showProgress();
        verify(signUpService).requestSingUp(eq(VALID_USERNAME), eq(VALID_PASSWORD), argumentCaptor.capture());

        argumentCaptor.getValue().onSuccess();

        verify(signUpView).hideProgress();
        verify(signUpView).showSuccessForSuccessfulSignUp();

    }

    @Test
    public void checkUnsuccessfulSignUp() {
        ArgumentCaptor<SignUpService.Callback> argumentCaptor
                = ArgumentCaptor.forClass(SignUpService.Callback.class);

        signUpPresenter.signUpClicked(VALID_USERNAME, VALID_PASSWORD);

        verify(signUpView).showProgress();
        verify(signUpService).requestSingUp(eq(VALID_USERNAME), eq(VALID_PASSWORD), argumentCaptor.capture());

        argumentCaptor.getValue().onError();

        verify(signUpView).hideProgress();
        verify(signUpView).showErrorForUnsuccessfulSignUp();

    }


}
