package gulerbu.com.tddsignupexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    @BindView(R.id.sign_up_edittext_username)
    EditText editTextUsername;

    @BindView(R.id.sign_up_edittext_password)
    EditText editTextPassword;

    private SignUpPresenter presenter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        presenter = new SignUpPresenter(this, new SignUpService());

    }

    @OnClick(R.id.sign_up_button_continue)
    public void onContinueClicked() {
        presenter.signUpClicked(editTextUsername.getText().toString(), editTextPassword.getText().toString());

    }


    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, null, "Loading", true);

    }

    @Override
    public void showErrorForUnsuccessfulSignUp() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(editTextPassword, "Unsuccessful SignUp!", Snackbar.LENGTH_LONG).show();
            }
        }, 1100);


    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 1000);

        }

    }

    @Override
    public void showSuccessForSuccessfulSignUp() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SignUpActivity.this, WelcomeActivity.class));
            }
        }, 1100);

    }

    @Override
    public void showErrorMessageForInvalidPasswordOrUserName() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(editTextPassword, "Invalid username or password", Snackbar.LENGTH_LONG).show();
            }
        }, 1100);



    }
}
