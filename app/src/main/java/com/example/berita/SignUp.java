package com.example.berita;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
    }

    public void back(View view) {
        finish();
    }

    public void Login(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        Toast.makeText(SignUp.this, "Sign Up berhasil", Toast.LENGTH_SHORT).show();
        finish();
    }
}