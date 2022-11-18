package com.example.taskmanager.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemHomeBinding
import com.example.taskmanager.data.models.Task

class TaskAdapter() :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val taskList: ArrayList<Task> = arrayListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
    fun addTask(task: Task){
        taskList.add(0,task)
        notifyItemChanged(0)

    }

    inner class TaskViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(task: Task){
                binding.textTitle.text=task.title
                binding.textDesc.text=task.desc
            }


    }

}