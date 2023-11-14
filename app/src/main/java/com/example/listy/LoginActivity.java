package com.example.listy;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.chrono.MinguoChronology;

public class LoginActivity extends AppCompatActivity {

  private FirebaseAuth firebaseAuth;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);

    // Initialize Firebase
    firebaseAuth = FirebaseAuth.getInstance();

    // Check if the user is already authenticated
    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    if (currentUser != null) {
      // User is already authenticated, navigate to the home activity
      startActivity(new Intent(LoginActivity.this, MainActivity.class));
      finish();
    }

    Button loginButton = findViewById(R.id.login_button);
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Add your login logic here using Firebase Authentication
        // After successful login, navigate to the home activity
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
      }
    });
    TextView signUpTextView = findViewById(R.id.textViewSignUp);
    signUpTextView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Handle the click on the "Sign up" text
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        // Navigate the user to RegistrationActivity
      }
    });
  }
}
