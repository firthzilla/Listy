package com.example.listy;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.chrono.MinguoChronology;

public class LoginActivity extends AppCompatActivity {

  private FirebaseAuth firebaseAuth;
  private EditText editTextEmail, editTextPassword;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);

    // Initialize Firebase
    firebaseAuth = FirebaseAuth.getInstance();

    // Initialize views
    editTextEmail = findViewById(R.id.editTextEmail);
    editTextPassword = findViewById(R.id.editTextPassword);

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
        // Call the login method when the login button is clicked
        loginUser();
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

  private void loginUser() {
    // Add your login logic here using Firebase Authentication
    // After successful login, navigate to the home activity
    String email = editTextEmail.getText().toString().trim();
    String password = editTextPassword.getText().toString().trim();

    if (email.isEmpty() || password.isEmpty()) {
      Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
      return;
    }
    firebaseAuth.signInWithEmailAndPassword(email, password)
      .addOnCompleteListener(this, task -> {
        if (task.isSuccessful()) {
          // Login successful, navigate to the home activity
          startActivity(new Intent(LoginActivity.this, MainActivity.class));
          finish();
        } else {
          // Login failed, show an error message
          Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
        }
      });
  }
}
