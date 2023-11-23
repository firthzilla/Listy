package com.example.listy;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home_activity);
    Log.d(TAG, "onCreate: activity created one");

    // Initialize Firebase
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    // Check if the user is already authenticated
    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    if (currentUser != null) {
      Log.d(TAG, "onCreate: user already authenticated");

      // User is authenticated, navigate to the home activity
      startActivity(new Intent(MainActivity.this, HomeActivity.class));
      finish();
    } else {
      // User is not authenticated, navigate to the login activity
      Log.d(TAG, "onCreate: user not authenticated");
      startActivity(new Intent(MainActivity.this, LoginActivity.class));
      finish();
    }
  }
}

