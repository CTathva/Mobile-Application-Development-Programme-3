package com.akash.cp.vtu.vtuparta_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements Base{
    TextInputLayout mEditTextName, mEditTextPassword;
    Button mButton;
    public static final String Shared_PRIF_NAME = "MyPrefs";
    String mUserName, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        listener();
    }

    @Override
    public void init() {
        mEditTextName=(TextInputLayout)findViewById(R.id.userNameTextInputLayout);
        mEditTextPassword=(TextInputLayout)findViewById(R.id.passwordTextInputLayout);
        mButton=(Button)findViewById(R.id.login_button);
    }

    @Override
    public void listener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(Shared_PRIF_NAME, MODE_PRIVATE);
                 mUserName = prefs.getString("name", "name not found");
                 mPassword = prefs.getString("password", "");
                if (mUserName.equals(mEditTextName.getEditText().getText().toString()) && mPassword.equals(mEditTextPassword.getEditText().getText().toString()))
                {
                    Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}