package com.example.listy;

import java.util.ArrayList;
import java.util.List;

public class DetailedTask extends Task implements TaskItem {

  private String notes;
  private String dueDate;
  private String reminder;
  private List<SubTask> subTasks;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  public int id;

  public DetailedTask(int id, String title, boolean isCompleted, String notes, String dueDate, String reminder){
    super(id, title, isCompleted);
    this.notes = notes;
    this.dueDate = dueDate;
    this.reminder = reminder;
    this.subTasks = new ArrayList<>();
  }


  public String getNotes() {
    return notes;
  }
  public String getDueDate(){
    return  dueDate;
  }

  public String getReminder() {
    return reminder;
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
  public List<SubTask> getSubTasks() {
    return subTasks;
  }

  public void addSubTask(SubTask subTask) {
    subTasks.add(subTask);
  }

  public void removeSubTask(SubTask subTask) {
    subTasks.remove(subTask);
  }
}
class SubTask {
  private String title;
  private boolean isCompleted;

  public SubTask(String title) {
    this.title = title;
    this.isCompleted = false;
  }
  public String getTitle() {
    return title;
  }
  public boolean isCompleted() {
    return isCompleted;
  }
  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
