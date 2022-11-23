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


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {

            if (binding.editTitle.text.toString().isNotEmpty()) {
                saveTasks()
            } else {
                binding.editTitle.error = getString(R.string.save_error)
            }
        }
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