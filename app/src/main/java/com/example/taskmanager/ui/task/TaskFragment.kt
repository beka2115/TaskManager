package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.data.models.Task
import com.example.taskmanager.ui.home.HomeFragment


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var task:Task?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val value =it.getSerializable(HomeFragment.KEY_FOR_TASK)
            if(value!=null){
                task=value as Task
                binding.editTitle.setText(task?.title.toString())
                binding.editDesc.setText(task?.desc.toString())
                if(task!=null){
                    binding.btnSave.text="Update"
                }else{
                    binding.btnSave.text="Save"
                }

            }
        }


        binding.btnSave.setOnClickListener {
            if (binding.editTitle.text.toString().isNotEmpty()) {
                if(task!=null){
                    updateTask()
                }else saveTasks()
            } else {
                binding.editTitle.error = getString(R.string.save_error)
            }
        }
    }

    private fun updateTask() {
        task?.title=binding.editTitle.text.toString()
        task?.desc=binding.editDesc.text.toString()
        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()
    }

    fun saveTasks() {
        val data = Task(
            title = binding.editTitle.text.toString(),
            desc = binding.editDesc.text.toString()
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }


}