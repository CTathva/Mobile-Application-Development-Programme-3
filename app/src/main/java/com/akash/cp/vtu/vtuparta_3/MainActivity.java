package com.akash.cp.vtu.vtuparta_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements Base{
    TextInputLayout mEditTextName, mEditTextPassword;
    Button mButton;
    String mUserName, mPassword;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    public static final String Shared_PRIF_NAME = "MyPrefs";
     /*
    (?=.*\d)      #   must contains one digit from 0-9
  (?=.*[a-z])   #   must contains one lowercase characters
  (?=.*[A-Z])   #   must contains one uppercase characters
  (?=.*[@#$%])   #   must contains one special symbols in the list "@#$%"
              . #   match anything with previous condition checking
  {8,}        #   length at least 8 characters
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listener();

    }

    @Override
    public void init() {
        mEditTextName=(TextInputLayout) findViewById(R.id.userNameTextInputLayout);
        mEditTextPassword=(TextInputLayout)findViewById(R.id.passwordTextInputLayout);
        mButton=(Button)findViewById(R.id.signupbtn);
    }

    @Override
    public void listener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditTextPassword.setErrorEnabled(false);
                mUserName=mEditTextName.getEditText().getText().toString();
                mPassword=mEditTextPassword.getEditText().getText().toString();
                if(TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword) )
                {
                    Toast.makeText(MainActivity.this, "Enter All fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(PASSWORD_PATTERN.matcher(mPassword).matches())
                    {
                        SharedPreferences.Editor editor = getSharedPreferences(Shared_PRIF_NAME, MODE_PRIVATE).edit();
                        editor.putString("name", mUserName);
                        editor.putString("password", mPassword);
                        editor.apply();
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        finish();
                    }
                    else
                    {
                        mEditTextPassword.setErrorEnabled(true);
                        mEditTextPassword.setError("Invalid Password Pattern");
                    }

                }

            }
        });

    }

}