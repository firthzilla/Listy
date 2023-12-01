package com.example.listy;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
  private EditText editTextTask;
  private Button buttonAddTask;
  private RecyclerView recyclerViewTasks;
  private TaskAdapter taskAdapter;
  private List<TaskItem> taskList;

  private DrawerLayout drawerLayout;
  private ActionBarDrawerToggle actionBarDrawerToggle;
  private NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home_activity);

    // Initialize views
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    // Set up the drawer layout
    drawerLayout = findViewById(R.id.drawerLayout);
    actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    actionBarDrawerToggle.syncState();

    // Set up the FloatingActionButton
    FloatingActionButton fabButton = findViewById(R.id.fabButton);

    // Set click listener for the toggle button for the drawer
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

    // Set up the navigation view
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      // Handle navigation view item clicks
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
          // Handle item 1 click
        } else if (id == R.id.nav_create_new_list) {
          // Handle item 2 click
        }
        // Add more conditions for other menu items as needed

        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
      }
    });
    // Set click listener for the FAB
    fabButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Perform the action when the FAB is clicked
        Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
        startActivity(intent);
      }
    });
    TextView dateTextView = findViewById(R.id.dateTextView);
    // Set the current date dynamically
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault());
    String currentDate = sdf.format(new Date());
    dateTextView.setText(currentDate);

    editTextTask = findViewById(R.id.editTextTask);
    buttonAddTask = findViewById(R.id.buttonAddTask);
    recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
    //Button buttonRemoveCompleted = findViewById(R.id.buttonRemoveCompleted);
    //loadTasksFromSharedPreferences();
    taskList = new ArrayList<>();


    taskAdapter = new TaskAdapter(taskList);
    recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
    recyclerViewTasks.setAdapter(taskAdapter);

    buttonAddTask.setOnClickListener(v -> {
      String taskTitle = editTextTask.getText().toString().trim();
      if (!taskTitle.isEmpty()) {
        Task newTask = new Task(taskList.size() + 1, taskTitle, false);
        addTask(newTask);
        editTextTask.setText(""); // Clear the edit text after adding task
      }
    });

    loadTasksFromFirestore();

    //buttonRemoveCompleted.setOnClickListener(v -> removeCompletedTasks());

  }

  private void saveTasksToFirestore() {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Log.d(TAG, "saveTasksToFirestore: calling saveTasksToFirestore");
    CollectionReference tasksRef = db.collection("lists").document("listID").collection("tasks");

    for (TaskItem task : taskList) {
      // Convert TaskItem to a Map and add it to Firestore
      Map<String, Object> taskMap = new HashMap<>();
      taskMap.put("id", task.getId());
      taskMap.put("title", task.getTitle());
      taskMap.put("isCompleted", task.isCompleted());

      tasksRef.add(taskMap)
        .addOnSuccessListener(documentReference -> {
          Log.d(TAG, "saveTasksToFirestore: Task added with ID: " + documentReference.getId());
          // Task added successfully
        })
        .addOnFailureListener(e -> {
          // Handle failure
          Log.w(TAG, "saveTasksToFirestore: Error adding task", e);
          Toast.makeText(HomeActivity.this, "Error adding task to Firestore", Toast.LENGTH_SHORT).show();
        });
    }
  }

  private void loadTasksFromFirestore() {
    Log.d(TAG, "loadTasksFromFirestore: calling loadTasksFromFirestore");
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    db.collection("lists").document("listId").collection("tasks")
      .get()
      .addOnSuccessListener(queryDocumentSnapshots -> {
       //  taskList.clear();
        Log.d(TAG, "loadTasksFromFirestore: QueryDocumentSnapshots" + queryDocumentSnapshots);
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          // Convert Firestore document to TaskItem object
          int id = documentSnapshot.getLong("id").intValue();
          String title = documentSnapshot.getString("title");
          boolean isCompleted = documentSnapshot.getBoolean("isCompleted");

          Task task = new Task(id, title, isCompleted);
          taskList.add(task);
        }
        Log.d(TAG, "loadTasksFromFirestore: calling taskAdapter" + taskList);
        taskAdapter.notifyDataSetChanged();
      })
      .addOnFailureListener(e -> {
        Log.w(TAG, "loadTasksFromFirestore: Error getting documents: ", e);
        Toast.makeText(HomeActivity.this, "Error getting tasks from Firestore", Toast.LENGTH_SHORT).show();
      });
  }
  private void addTask(TaskItem task) {
    taskList.add(task);
    taskAdapter.notifyItemInserted(taskList.size() - 1);
    saveTasksToFirestore(); // Save to Firestore after adding a task
  }

  private void removeTask(int position) {
    if (position >= 0 && position < taskList.size()) {
      taskList.remove(position);
      taskAdapter.notifyItemRemoved(position);
      saveTasksToFirestore(); // Save to Firestore after removing a task
    }
  }

/*  private void updateTask(int position, TaskItem updatedTask) {
    if (position >= 0 && position < taskList.size()) {
      taskList.set(position, updatedTask);
      taskAdapter.notifyItemChanged(position);
      saveTasksToFirestore(); // Save to Firestore after updating a task
    }
  }*/
  private void clearAllTasks() {
    taskList.clear();
    taskAdapter.notifyDataSetChanged();
    saveTasksToFirestore(); // Save to Firestore after clearing all tasks
  }



  // Handle menu item clicks
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  // Load tasks from SharedPreferences options

/*  private void loadTasksFromSharedPreferences() {
    Log.d(TAG, "loadTasksFromSharedPreferences: load task method called");
    SharedPreferences sharedPreferences = getSharedPreferences("MyTasks", MODE_PRIVATE);

    Gson gson = new Gson();
    String tasksJson = sharedPreferences.getString("taskList", "");

    Type taskListType = new TypeToken<List<Task>>() {}.getType();
    taskList = gson.fromJson(tasksJson, taskListType);

    if (taskList == null) {
      taskList = new ArrayList<>();
    }
  }
  // Save tasks to SharedPreferences to change to firestore
  private void saveTasksToSharedPreferences() {
    Log.d(TAG, "saveTasksToSharedPreferences: save task method called");
    SharedPreferences sharedPreferences = getSharedPreferences("MyTasks", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    Gson gson = new Gson();
    String tasksJson = gson.toJson(taskList);

    editor.putString("taskList", tasksJson);
    editor.apply();
  }

  // Remove completed tasks to adjust with firestore
  private void removeCompletedTasks() {
    taskList.removeIf(Task::isCompleted);
    saveTasksToSharedPreferences();
    taskAdapter.notifyDataSetChanged();

  }

  // Add tasks to adjust with firestore
  private void addTask() {
    String taskTitle = editTextTask.getText().toString().trim();
    if (!taskTitle.isEmpty()) {
      Task newTask = new Task(taskList.size() + 1, taskTitle, false);
      taskList.add(newTask);
      saveTasksToSharedPreferences();
      taskAdapter.notifyDataSetChanged();
      editTextTask.setText("");

    }
  }*/
}



