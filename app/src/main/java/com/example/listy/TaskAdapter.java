package com.example.listy;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
  private List<Task> taskList;

  public TaskAdapter(List<Task> taskList){
    this.taskList = taskList;
  }

  @Override
  public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
    return new TaskViewHolder(view);
  }

  @Override
  public void onBindViewHolder(TaskViewHolder holder, int position) {
    Task task = taskList.get(position);
    holder.textViewTitle.setText(task.getTitle());
    holder.checkBoxTask.setChecked(task.isCompleted());

    holder.checkBoxTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        task.setCompleted(isChecked);
      }
    });
  }

  public int getItemCount() {
    return taskList.size();
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
