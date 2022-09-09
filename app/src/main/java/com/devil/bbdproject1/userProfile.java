package com.devil.bbdproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userProfile extends AppCompatActivity {
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        database = FirebaseDatabase.getInstance();
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        database.getReference("Profile").child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                TextView name = findViewById(R.id.nameValue);
                name.setText(snapshot.child("name").getValue().toString());
                TextView nameKey = findViewById(R.id.nameKey);
                nameKey.setText(snapshot.child("name").getKey().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(userProfile.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}