package com.example.weddingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MediaPlayer ply;
    Button play, logInButton, signUpButton;
    EditText email , password;
    String txtEmail , txtpassword;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Intent intent;
    Intent login;
    DatabaseReference mReferance;
    public String Username;
    public List<String> UserData = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.playbtn);
        ply = MediaPlayer.create(MainActivity.this, R.raw.themesong);
        ply.start();
        ply.setLooping(true);
        logInButton=findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signupButton);
        email= (EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();



        mUser=mAuth.getCurrentUser();
        //Username = mUser.getDisplayName();









        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtEmail =email.getText().toString();
                txtpassword= password.getText().toString();
                if(!TextUtils.isEmpty(txtEmail)&&!TextUtils.isEmpty(txtpassword)){
                    mAuth.signInWithEmailAndPassword(txtEmail,txtpassword).addOnSuccessListener(MainActivity.this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser=mAuth.getCurrentUser();
                            mReferance = FirebaseDatabase.getInstance().getReference("users").child(mUser.getUid());
                            System.out.println(mUser.getDisplayName());
                            mReferance.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    //parcalı sekilde verileri almak için for kullanıldı
                                    for(DataSnapshot snap: snapshot.getChildren()) {


                                        UserData.add(snap.getValue().toString());
                                        System.out.println(snap.getKey() + "=" +snap.getValue());
                                    }
                                    System.out.println(UserData);
                                    Username = UserData.get(3);


                                    login = new Intent(getBaseContext(),Home.class);
                                    login.putExtra("UserName",Username);
                                    startActivity(login);





                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();


                                }
                            });








                            // Toast.makeText(MainActivity.this,mAuth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    });


                }else{
                    Toast.makeText(MainActivity.this, "Email and password can not be empty" , Toast.LENGTH_SHORT ).show();


                }



            }


        });





        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getBaseContext(), Register.class);
                startActivity(intent);
            }
        });



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ply.isPlaying()) {
                    ply.pause();
                } else {
                    ply.start();
                }

            }
        });


    }


}
