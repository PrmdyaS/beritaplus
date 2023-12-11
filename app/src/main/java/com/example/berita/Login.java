package com.example.berita;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        if (username.equals("admin") && password.equals("admin123")) {
            Toast.makeText(Login.this, "Login berhasil", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(Login.this, "Login gagal. Periksa username dan password", Toast.LENGTH_SHORT).show();
        }
    }
}