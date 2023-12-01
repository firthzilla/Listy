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
  private FloatingActionButton addTaskButton;

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
    addTaskButton = findViewById(R.id.addTaskButton);

    // Set click listener for "Add Task" button
    //this is the code that will be executed when the button is clicked it needs to send the data to the database then close the activity
/*    addTaskButton.setOnClickListener(v -> {
      String taskTitle = editTextTask.getText().toString().trim();
      // Create a DetailedTask or any class implementing TaskItem interface
      if (!taskTitle.isEmpty()) {
        DetailedTask newDetailedTask = new DetailedTask(taskList.size() + 1, taskTitle, *//* other parameters *//*);
        addTask(newDetailedTask);
        editTextTask.setText(""); // Clear the edit text after adding task
      }
    });*/

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
  }
}
