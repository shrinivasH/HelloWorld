package com.example.shrinivas.myhelloworldapplication.activities;

import android.os.PatternMatcher;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shrinivas.myhelloworldapplication.R;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {
    @BindView(R.id.emailId)
    EditText mEmailId;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.loginBtn)
    Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        progressBarHandler(this);

    }

    @OnClick(R.id.loginBtn)
    public void loginButtonClick() {
        boolean result = callToValidation();
        showProgressBar();
        if (result) {
            Toast.makeText(getApplicationContext(), "All Validations are done", Toast.LENGTH_LONG).show();
            hideProgressBar();
        }
    }

    private boolean callToValidation() {
        String emailId = mEmailId.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (mEmailId.getText().toString().trim().isEmpty()) {
            mEmailId.setError("Please enter email id");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            mEmailId.setError("Invalid email id");
            return false;
        } else if (password.isEmpty()) {
            mPassword.setError("Please enter your password");
            return false;
        }
        return true;
    }
}
