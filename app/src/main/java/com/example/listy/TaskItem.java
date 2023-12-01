package com.example.listy;

public interface TaskItem {
  String getTitle();
  boolean isCompleted();
  void setCompleted(boolean completed);
  int getId();
}
