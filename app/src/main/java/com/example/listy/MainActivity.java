package com.example.listy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {
  private EditText editTextTask;
  private Button buttonAddTask;
  private RecyclerView recyclerViewTasks;
  private TaskAdapter taskAdapter;
  private List<Task> taskList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editTextTask = findViewById(R.id.editTextTask);
    buttonAddTask = findViewById(R.id.buttonAddTask);
    recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
    Button buttonRemoveCompleted = findViewById(R.id.buttonRemoveCompleted);
    taskList = new ArrayList<>();
    taskAdapter = new TaskAdapter(taskList);

    recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
    recyclerViewTasks.setAdapter(taskAdapter);

    buttonAddTask.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        addTask();
      }
    });
    buttonRemoveCompleted.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        removeCompletedTasks();
      }
    });

  }

  private void removeCompletedTasks() {
    Iterator<Task> iterator = taskList.iterator();
    while (iterator.hasNext()) {
      Task task = iterator.next();
      if (task.isCompleted()) {
        iterator.remove();
      }
    }
    taskAdapter.notifyDataSetChanged();
  }

  private void addTask() {
    String taskTitle = editTextTask.getText().toString().trim();
    if (!taskTitle.isEmpty()) {
      Task newTask = new Task(taskList.size() + 1, taskTitle);
      taskList.add(newTask);
      taskAdapter.notifyDataSetChanged();
      editTextTask.setText("");
    }
  }


}



