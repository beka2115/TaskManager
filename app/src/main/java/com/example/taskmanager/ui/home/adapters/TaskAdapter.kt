package com.example.taskmanager.ui.home.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemHomeBinding
import com.example.taskmanager.data.models.Task

class TaskAdapter(
    val onLongClick: (position: Int) -> Unit,
    val onClick: (Task) -> Unit
) :
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

    fun addTasks(newTasks: List<Task>) {
        this.taskList.clear()
        this.taskList.addAll(newTasks)
        notifyDataSetChanged()
    }

    fun deleteTask(task: Task) {
        App.db.taskDao().delete(task)
    }


   /* fun taskColor(taskList: List<Task>){
        for (i in)
            this.taskList[2].
    }*/

    fun sendTask(position: Int): Task {
        return taskList[position]
    }


    inner class TaskViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {



        @SuppressLint("ResourceAsColor")
        fun bind(task: Task) {
            itemView.setOnLongClickListener {
                onLongClick(adapterPosition)

                return@setOnLongClickListener false
            }
            for (i in 1 until taskList.size step 2){
                if (adapterPosition == i){
                    itemView.setBackgroundColor(Color.BLACK)
                    binding.textDesc.setTextColor(Color.WHITE)
                    binding.textTitle.setTextColor(Color.WHITE)

                }
            }


            itemView
            binding.root.setOnClickListener{
                onClick(task)
            }
            binding.textTitle.text = task.title
            binding.textDesc.text = task.desc

        }


    }

}