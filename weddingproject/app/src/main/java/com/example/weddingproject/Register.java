package com.example.weddingproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText UsernameInput, passwordET,email,password ;
    Button buttonRegister;
    String txtEmail , txtpassword, txtUsername ;
    ToggleButton registerCharTB;
    FirebaseAuth mAuth;
    DatabaseReference mReferance;
    HashMap<String, Object> mData;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        UsernameInput = (EditText) findViewById(R.id.UsernameInputET);
        passwordET = (EditText) findViewById(R.id.PasswordET);
        registerCharTB = (ToggleButton) findViewById(R.id.registerCharTB);
        email= (EditText)findViewById(R.id.registeremail);
        password=(EditText)findViewById(R.id.PasswordET);
        mAuth=FirebaseAuth.getInstance();
        mReferance= FirebaseDatabase.getInstance().getReference();
        buttonRegister =(Button)findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmail = email.getText().toString();
                txtpassword = password.getText().toString();
                txtUsername=UsernameInput.getText().toString();

                if(! TextUtils.isEmpty(txtUsername)&&!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtpassword)){
                    mAuth.createUserWithEmailAndPassword(txtEmail,txtpassword)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        mUser= mAuth.getCurrentUser();
                                        mData= new HashMap<>();
                                        mData.put("username",txtUsername);
                                        mData.put("user email", txtEmail);
                                        mData.put("user password", txtpassword);
                                        mData.put("user ıd",mUser.getUid());

                                        mReferance.child("users").child(mUser.getUid())
                                                .setValue(mData)
                                                .addOnCompleteListener(Register.this, new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(Register.this, "registration is successful", Toast.LENGTH_SHORT).show();
                                                        } else
                                                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();




                                                    }
                                                });

                                        //Toast.makeText(Register.this, "register action done",Toast.LENGTH_SHORT).show();

                                    }
                                    else{
                                        Toast.makeText(Register.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(Register.this,"email and password can not be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
