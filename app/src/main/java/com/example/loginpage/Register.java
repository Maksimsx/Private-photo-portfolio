package com.example.loginpage;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper dbHelper;
    private Button buttonRegister;
    public EditText edtTextRegisterEmail, edtTextRegisterUsername, edtTextRegisterPassword, edtTextRegisterPasswordRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        buttonRegister = findViewById(R.id.btnRegister);
        edtTextRegisterEmail = findViewById(R.id.edtTextRegisterEmail);
        edtTextRegisterUsername = findViewById(R.id.edtTextRegisterUsername);
        edtTextRegisterPassword = findViewById(R.id.edtTextRegisterPassword);
        edtTextRegisterPasswordRepeat = findViewById(R.id.edtTextRegisterPasswordRepeat);

        signUp();

    }

    public void signUp(){
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dbEmail = edtTextRegisterEmail.getText().toString();
                String dbUsername = edtTextRegisterUsername.getText().toString();
                String dbPassword = edtTextRegisterPassword.getText().toString();
                String dbPasswordRepeat = edtTextRegisterPasswordRepeat.getText().toString();

                if(dbUsername.equals("")||dbPassword.equals("")||dbEmail.equals("")||dbPasswordRepeat.equals("")){
                    Toast.makeText(Register.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(dbPassword.equals(dbPasswordRepeat)){
                        Boolean checkUser = dbHelper.checkData(dbUsername);
                        if(checkUser == false){
                            Boolean insert = dbHelper.addData(dbEmail, dbUsername, dbPassword);
                            if(insert==true){
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, FirstPage.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Register.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}