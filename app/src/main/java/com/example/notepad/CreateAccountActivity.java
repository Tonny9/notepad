package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity {

    EditText emailEditTxt,passEditTxt,confPassEditTxt;
    Button createAccountBtn;
    TextView logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailEditTxt = findViewById(R.id.email_edit_text);
        passEditTxt = findViewById(R.id.pass_edit_text);
        confPassEditTxt = findViewById(R.id.confirm_password_edit_text);
        createAccountBtn = findViewById(R.id.create_account_btn);
        logIn = findViewById(R.id.login_textview_btn);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        createAccountBtn.setOnClickListener((v) -> createAccount());
    }
    void createAccount(){
      String email = emailEditTxt.getText().toString();
      String password = passEditTxt.getText().toString();
      String confirmPassword = confPassEditTxt.getText().toString();

      boolean isValidated = validateData(email,password,confirmPassword);
    }
    boolean validateData(String email,String password,String confirmPassword){
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditTxt.setError("Email is invalid");
            return false;
        }
        if (password.length()<6){
            passEditTxt.setError("password length is invalid");
            return false;
        }
        if (!password.equals(confirmPassword)){
            confPassEditTxt.setError("password not matched");
            return false;

        }
        return true;
    }

}