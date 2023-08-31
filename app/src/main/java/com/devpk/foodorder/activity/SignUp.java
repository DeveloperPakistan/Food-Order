package com.devpk.foodorder.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.devpk.foodorder.Contants;
import com.devpk.foodorder.R;
import com.devpk.foodorder.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {


    private MaterialEditText edit_PhoneNumber, edit_Name, edit_Password;
    private BootstrapButton btnSign_Up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(Contants.url);
        DatabaseReference tableUser = firebaseDatabase.getReference("User");

        edit_PhoneNumber = findViewById(R.id.edit_PhoneNumber);
        edit_Name = findViewById(R.id.edit_Name);
        edit_Password = findViewById(R.id.edit_Password);

        btnSign_Up = findViewById(R.id.btnSign_Up);

        btnSign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Please Waiting.....");
                progressDialog.show();

                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.child(edit_PhoneNumber.getText().toString()).exists()) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Phone Number Already Register ", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            User user = new User(edit_Name.getText().toString(), edit_Password.getText().toString());
                            tableUser.child(edit_PhoneNumber.getText().toString()).setValue(user);
                            Toast.makeText(getApplicationContext(), "Sign Up successfully ", Toast.LENGTH_SHORT).show();
                            finish();
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