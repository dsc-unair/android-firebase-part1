package com.dscunair.rc02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText email, password;
    private MaterialButton btnLogin;
    private ProgressBar progressBar;
    private TextView toRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
        toRegister = findViewById(R.id.to_register);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    showLoading();
                    String textEmail = email.getText().toString();
                    String textPassword = password.getText().toString();

                    mAuth.signInWithEmailAndPassword(textEmail, textPassword)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    hideLoading();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    hideLoading();
                                    Toast.makeText(
                                            LoginActivity.this,
                                            e.getLocalizedMessage(),
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            });
                }
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validate() {
        if (TextUtils.isEmpty(email.getText())) {
            email.setError("Email tidak boleh kosong");
            email.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password.getText())) {
            password.setError("Password tidak boleh kosong");
            password.requestFocus();
            return false;
        }
        return true;
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
