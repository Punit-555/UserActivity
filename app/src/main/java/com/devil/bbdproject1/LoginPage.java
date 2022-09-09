package com.devil.bbdproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    FirebaseAuth mauth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);

        Button LogoutN = findViewById(R.id.logoutBTN);
        Button login = findViewById(R.id.login);
        TextView crtACC = findViewById(R.id.crtACC);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView emailTV = findViewById(R.id.emailTV);
                TextView passTV = findViewById(R.id.passwordTV);
                String email = emailTV.getText().toString() ;
                String pass = passTV.getText().toString();
                loginUser(email, pass);
            }
        });
        
        crtACC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView emailTV = findViewById(R.id.emailTV);
                TextView passTV = findViewById(R.id.passwordTV);
                String email = emailTV.getText().toString() ;
                String pass = passTV.getText().toString();
                createUser(email, pass);
            }
        });
        }

    @Override
    protected void onStart() {
        super.onStart();
        if(mauth.getCurrentUser() != null){
            Intent intent= new Intent(LoginPage.this,Dashboard.class);
            startActivity(intent);
        }
    }

    private void createUser(String email, String pass) {
        mauth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent= new Intent(LoginPage.this,userDetails.class);
                    FirebaseAuth.getInstance().getCurrentUser().updateEmail(email);
                    startActivity(intent);
                    Toast.makeText(LoginPage.this, "Successful...", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginPage.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser(String email, String pass) {
        mauth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent= new Intent(LoginPage.this,Dashboard.class);
                    startActivity(intent);
                    Toast.makeText(LoginPage.this, "Welcome", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginPage.this,"Retry after some time...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}