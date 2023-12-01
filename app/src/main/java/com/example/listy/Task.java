package com.example.listy;

public class Task implements TaskItem{
  private int id;
  private String title;
  private boolean isCompleted;


  public Task(int id, String title, boolean isCompleted) {
    this.id = id;
    this.title = title;
    this.isCompleted = false;
  }

  public String getTitle() {
    return title;
  }
  public int getId(){
    return id;
  }


  public void setTitle(String newTitle){
    this.title = newTitle;
  }

  public void setId(int newId){
    this.id = newId;
  }

  public boolean isCompleted() {
    return isCompleted;
  }


  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }
}
