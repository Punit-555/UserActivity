package com.devil.bbdproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class userDetails extends AppCompatActivity {
    FirebaseDatabase database;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        TextView name = findViewById(R.id.nameTV);
        TextView email = findViewById(R.id.emailTV);
        TextView phone = findViewById(R.id.phoneTV);
        TextView college = findViewById(R.id.collegeTV);
        Button submit = findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                uploadData(name,email,phone,college);
            }
        });

        database = FirebaseDatabase.getInstance();

    }

    private void uploadData(TextView name, TextView email, TextView phone, TextView college) {
                dataUser data = new dataUser(name.getText().toString(),
                email.getText().toString()
                ,phone.getText().toString()
                ,college.getText().toString());
                String Useremail = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                database.getReference("Profile")
                .child(Useremail)
                .setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(userDetails.this,
                                               "Successfully uploaded", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(userDetails.this, Dashboard.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(userDetails.this, "Upload Failed: "+e.toString(),
                                               Toast.LENGTH_SHORT).show();
                                email.setText("");
                                name.setText("");
                                college.setText("");
                                phone.setText("");
                            }
                        });

    }
}