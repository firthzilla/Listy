package com.example.listy;

public class DetailedTask {
  private int id;
  private String title;
  private String notes;
  private String dueDate;
  private String reminder;
  private boolean isCompleted;

  public DetailedTask(int id, String title, String notes, String dueDate, String reminder, boolean isCompleted){
    this.id = id;
    this.title = title;
    this.notes = notes;
    this.dueDate = dueDate;
    this.reminder = reminder;
    this.isCompleted = isCompleted;

  }

  public int getId() {
    return id;
  }
  public String getTitle() {
    return  title;
  }
  public String getNotes() {
    return notes;
  }
  public String getDueDate(){
    return  dueDate;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public String getReminder() {
    return reminder;
  }

  public void setId(int id) {
    this.id = id;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setNotes(String notes){
    this.notes = notes;
  }
  public void setDueDate(String dueDate){
    this.dueDate = dueDate;
  }
  public void setReminder(String reminder){
    this.reminder = reminder;
  }
  public void setCompleted(boolean isCompleted) {
    this.isCompleted = isCompleted;
  }
}
