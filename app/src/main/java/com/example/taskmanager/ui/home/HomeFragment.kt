package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.adapters.TaskAdapter

class HomeFragment : Fragment() {
    private lateinit var adapter: TaskAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClick)
        builder = AlertDialog.Builder(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = App.db.taskDao().getAllTask()
        adapter.addTasks(data)
        binding.recyclerHome.adapter = adapter
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onLongClick(position: Int) {
        builder.setTitle("Delete").setMessage("Do you want to delete?")
            .setCancelable(true)
            .setPositiveButton("Yes") { dialogInterface, _ ->
                adapter.deleteTask(adapter.sendTask(position))
                dialogInterface.dismiss()
                findNavController().navigate(R.id.navigation_home)
            }
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .show()
    }
}