package com.example.listy;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
  private List<TaskItem> taskList;

  public TaskAdapter(List<TaskItem> taskList) {
    this.taskList = taskList;
  }

  @Override
  public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Log.d(TAG, "onCreateViewHolder: onCreateViewHolder called");
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quick_task_item, parent, false);
    return new TaskViewHolder(view);
  }

  @Override
  public void onBindViewHolder(TaskViewHolder holder, int position) {
    Log.d(TAG, "onBindViewHolder: onBindViewHolder called");
    TaskItem taskItem = taskList.get(position);
    holder.textViewTitle.setText(taskItem.getTitle());
    holder.checkBoxTask.setChecked(taskItem.isCompleted());

    holder.checkBoxTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
      taskItem.setCompleted(isChecked);
    });
  }

  @Override
  public int getItemCount() {
    return taskList.size();
  }

  public void setTaskList(List<TaskItem> taskList) {
    this.taskList = taskList;
    notifyDataSetChanged();
  }

  public class TaskViewHolder extends RecyclerView.ViewHolder {
    CheckBox checkBoxTask;
    TextView textViewTitle;

    public TaskViewHolder(View itemView) {
      super(itemView);
      checkBoxTask = itemView.findViewById(R.id.checkBoxTask);
      textViewTitle = itemView.findViewById(R.id.textViewTitle);
    }
  }
}
