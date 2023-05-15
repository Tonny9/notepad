package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditTxt,passEditTxt;
    Button loginBtn;
    TextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailEditTxt = findViewById(R.id.email_edit_text);
        passEditTxt = findViewById(R.id.pass_edit_text);
        loginBtn = findViewById(R.id.login);
        signIn = findViewById(R.id.textView4);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,CreateAccountActivity.class);
                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                String email = emailEditTxt.getText().toString();
                String password = passEditTxt.getText().toString();

                Toast.makeText(getApplicationContext(), "email: " + email + "\nPassword: " + password + " ", Toast.LENGTH_SHORT).show();


            }
        });
    }
}