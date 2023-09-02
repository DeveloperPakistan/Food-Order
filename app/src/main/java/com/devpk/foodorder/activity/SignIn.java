package com.devpk.foodorder.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.devpk.foodorder.Common;
import com.devpk.foodorder.Contants;
import com.devpk.foodorder.Home;
import com.devpk.foodorder.R;
import com.devpk.foodorder.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    private MaterialEditText editPhoneNumber, editPassword;
    private BootstrapButton btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editPassword = findViewById(R.id.editPassword);
        btnSignin = findViewById(R.id.btnSignin);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(Contants.url);
        DatabaseReference tableUser = firebaseDatabase.getReference("User");

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Please Waiting.....");
                progressDialog.show();
                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Get User Information
                        if (snapshot.child(editPhoneNumber.getText().toString()).exists()) {
                            User user = snapshot.child(editPhoneNumber.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(editPassword.getText().toString())) {
                                progressDialog.dismiss();
                                Intent home = new Intent(SignIn.this, Home.class);
                                Common.currentUser = user;
                                startActivity(home);
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Sign In fail", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "User doesn't Exist Database", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}