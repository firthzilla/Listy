package com.example.listy;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

  private FirebaseAuth firebaseAuth;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Initialize Firebase
    firebaseAuth = FirebaseAuth.getInstance();

    // Check if the user is already authenticated
    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    if (currentUser != null) {
      // User is authenticated, navigate to the home activity
      startActivity(new Intent(MainActivity.this, HomeActivity.class));
      finish();
    } else {
      // User is not authenticated, navigate to the login activity
      startActivity(new Intent(MainActivity.this, LoginActivity.class));
      finish();
    }
  }
}

