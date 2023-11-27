package com.example.listy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddTaskActivity extends AppCompatActivity {
  private EditText taskNameEditText;
  private EditText notesEditText;
  private Button addToListButton;
  private Button dueDateButton;
  private Button reminderButton;
  private Button repeatButton;
  private FloatingActionButton fabButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_task);

    // Initialize views
    taskNameEditText = findViewById(R.id.taskName);
    notesEditText = findViewById(R.id.editTextNotes);
    addToListButton = findViewById(R.id.addToListSpinner);
    dueDateButton = findViewById(R.id.dueDatePicker);
    reminderButton = findViewById(R.id.reminderDatePicker);
    repeatButton = findViewById(R.id.repeatSpinner);
    fabButton = findViewById(R.id.fabButton);

    // Set onClick listeners for buttons
    addToListButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Handle click for "Add to List" button
      }
    });

    dueDateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Handle click for "Due Date" button
      }
    });

    reminderButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Handle click for "Reminder" button
      }
    });

    repeatButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Handle click for "Repeat" button
      }
    });

    fabButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Handle click for Floating Action Button (Fab)
      }
    });
  }
}
