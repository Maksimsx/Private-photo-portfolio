package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstPage extends AppCompatActivity {


    private EditText username, password;
    private Button loginBtn, registerBtn;
    private TextView txtSmth;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextTextPassword);
        loginBtn = findViewById(R.id.btnLogin);
        txtSmth = findViewById(R.id.txtForgotPassword);
        registerBtn = findViewById(R.id.btnMoveRegister);

        login();
        register();
    }

    private void register() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstPage.this, Register.class);
                startActivity(i);
            }
        });
    }


    private void login() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")|| pass.equals("")){
                    Toast.makeText(FirstPage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkUserPass = databaseHelper.checkUsernamePassword(user, pass);
                    if(checkUserPass == true){
                        Toast.makeText(FirstPage.this, "Sign in successfull!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FirstPage.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(FirstPage.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}