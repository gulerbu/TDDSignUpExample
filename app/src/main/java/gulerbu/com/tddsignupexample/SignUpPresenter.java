/*
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.tddsignupexample;


public class SignUpPresenter {

    private SignUpView signUpView;

    private SignUpService signUpService;

    public SignUpPresenter(SignUpView signUpView, SignUpService signUpService) {
        this.signUpView = signUpView;
        this.signUpService = signUpService;

    }

    public void signUpClicked(String username, String password) {
        signUpView.showProgress();
        signUp(username, password);

    }


    public void signUp(String username, String password) {

        if (!InputValidationUtil.isUsernameValid(username) || !InputValidationUtil.isPasswordValid(password)) {
            signUpView.hideProgress();
            signUpView.showErrorMessageForInvalidPasswordOrUserName();
            return;
        }

        signUpService.requestSingUp(username, password, new SignUpService.Callback() {
            @Override
            public void onSuccess() {
                signUpView.hideProgress();
                signUpView.showSuccessForSuccessfulSignUp();
            }

            @Override
            public void onError() {
                signUpView.hideProgress();
                signUpView.showErrorForUnsuccessfulSignUp();

            }
        });

    }
}
