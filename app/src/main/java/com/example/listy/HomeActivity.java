package com.example.listy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
  private EditText editTextTask;
  private Button buttonAddTask;
  private RecyclerView recyclerViewTasks;
  private TaskAdapter taskAdapter;
  private List<Task> taskList;

  private DrawerLayout drawerLayout;
  private ActionBarDrawerToggle actionBarDrawerToggle;
  private NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home_activity);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawerLayout = findViewById(R.id.drawerLayout);
    actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    actionBarDrawerToggle.syncState();

    // Set click listener for the toggle button
    actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
          drawerLayout.closeDrawer(GravityCompat.START);
        } else {
          drawerLayout.openDrawer(GravityCompat.START);
        }
      }
    });

    navigationView = findViewById(R.id.navigationView);


    TextView dateTextView = findViewById(R.id.dateTextView);

    // Set the current date dynamically
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault());
    String currentDate = sdf.format(new Date());
    dateTextView.setText(currentDate);

    editTextTask = findViewById(R.id.editTextTask);
    buttonAddTask = findViewById(R.id.buttonAddTask);
    recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
    //Button buttonRemoveCompleted = findViewById(R.id.buttonRemoveCompleted);
    loadTasksFromSharedPreferences();
    taskAdapter = new TaskAdapter(taskList);
    recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
    recyclerViewTasks.setAdapter(taskAdapter);
    buttonAddTask.setOnClickListener(v -> addTask());
    //buttonRemoveCompleted.setOnClickListener(v -> removeCompletedTasks());

  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void loadTasksFromSharedPreferences() {
    SharedPreferences sharedPreferences = getSharedPreferences("MyTasks", MODE_PRIVATE);

    Gson gson = new Gson();
    String tasksJson = sharedPreferences.getString("taskList", "");

    Type taskListType = new TypeToken<List<Task>>() {}.getType();
    taskList = gson.fromJson(tasksJson, taskListType);

    if (taskList == null) {
      taskList = new ArrayList<>();
    }
  }
  private void saveTasksToSharedPreferences() {
    SharedPreferences sharedPreferences = getSharedPreferences("MyTasks", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    Gson gson = new Gson();
    String tasksJson = gson.toJson(taskList);

    editor.putString("taskList", tasksJson);
    editor.apply();
  }


  private void removeCompletedTasks() {
    taskList.removeIf(Task::isCompleted);
    saveTasksToSharedPreferences();
    taskAdapter.notifyDataSetChanged();

  }

  private void addTask() {
    String taskTitle = editTextTask.getText().toString().trim();
    if (!taskTitle.isEmpty()) {
      Task newTask = new Task(taskList.size() + 1, taskTitle);
      taskList.add(newTask);
      saveTasksToSharedPreferences();
      taskAdapter.notifyDataSetChanged();
      editTextTask.setText("");

    }
  }
}



