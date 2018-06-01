package com.example.lagun.play4me4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordCView;
    private AutoCompleteTextView mNameView;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView= (EditText)findViewById(R.id.password);
        mPasswordCView= (EditText)findViewById(R.id.passwordConfirm);
        mNameView=(AutoCompleteTextView) findViewById(R.id.username);
        mSpinner=(Spinner)findViewById(R.id.spinner);/**/

        Button mEmailSignInButton = (Button) findViewById(R.id.email_register_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });


    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4 && !password.contains(":");
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@") && !email.contains(":");
    }

    protected void attemptRegister(){

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordCView.setError(null);
        mNameView.setError(null);
        TextView spinnerError = (TextView)mSpinner.getSelectedView();

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordc = mPasswordCView.getText().toString();
        String userName = mNameView.getText().toString();
        String accountType=mSpinner.getSelectedItem().toString();

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if(!isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;

        }
        if (!password.equals(passwordc)) {
            mPasswordCView.setError(getString(R.string.error_no_match_password));
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (TextUtils.isEmpty(userName)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }
        if (accountType.equals("Tipologia Account")) {
            spinnerError.setError("");
            spinnerError.setTextColor(Color.RED);//just to highlight that this is an error
            spinnerError.setText(R.string.error_no_type_account);//changes the selected item text to this
            focusView = mSpinner;
            cancel = true;
        }

        if(!cancel){
            ObjectFactory.addRegistered(new User(userName,email.toLowerCase(),password,accountType.equals("Locale")));
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }
}
