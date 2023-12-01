package com.example.listy;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
  private String listName;
  private List<Task> tasks;


  public TaskList() {
    this.listName = listName;
    tasks = new ArrayList<>();
  }

  public void setListName(String listName) {
    this.listName = listName;
  }
  public String getListName() {
    return listName;
  }
  public List<Task> getTasks() {
    return tasks;
  }


  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void removeTask(Task task) {
    tasks.remove(task);
  }
  public List<TaskItem> getTaskItems() {
    List<TaskItem> taskItems = new ArrayList<>();
    for (Task task : tasks) {
      // Convert each Task object to TaskItem (if TaskItem is your extended class)
      // For instance, if TaskItem is a subclass of Task:

      TaskItem taskItem = new TaskItem() {
        @Override
        public String getTitle() {
          return task.getTitle();
        }

        @Override
        public boolean isCompleted() {
          return task.isCompleted();
        }

        @Override
        public void setCompleted(boolean completed) {

        }

        @Override
        public int getId() {
          return 0;
        }
      };
      taskItems.add(taskItem);
    }
    return taskItems;
  }

}
