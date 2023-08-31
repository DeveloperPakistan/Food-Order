package com.devpk.foodorder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.devpk.foodorder.R;

public class MainActivity extends AppCompatActivity {

    private BootstrapButton btnSignup, btnSignIn;
    private TextView txtSologan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignup = findViewById(R.id.btnSignup);
        btnSignIn = findViewById(R.id.btnSignIn);

        txtSologan = findViewById(R.id.txtSologan);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
        txtSologan.setTypeface(typeface);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUpIntent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = new Intent(MainActivity.this, SignIn.class);
                startActivity(signInIntent);
            }
        });

    }
}