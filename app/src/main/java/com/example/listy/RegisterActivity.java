package com.example.listy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

  private FirebaseAuth firebaseAuth;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);

    firebaseAuth = FirebaseAuth.getInstance();

    EditText emailEditText = findViewById(R.id.editTextEmailRegister);
    EditText passwordEditText = findViewById(R.id.editTextPasswordRegister);
    Button registerButton = findViewById(R.id.register_button);

    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Register the user with Firebase Authentication
        firebaseAuth.createUserWithEmailAndPassword(email, password)
          .addOnCompleteListener(RegisterActivity.this, task -> {
            if (task.isSuccessful()) {
              // Registration successful, navigate to the home activity or login page
              startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
              finish();
            } else {
              // Registration failed
              Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.",
                Toast.LENGTH_SHORT).show();
              // Handle failure (display error message, etc.)
            }
          });
      }
    });
  }
}

