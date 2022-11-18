package com.example.taskmanager.ui.Boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.ui.home.adapters.OnBoardingAdapter
import kotlinx.android.synthetic.main.fragment_on_boarding.*

import me.relex.circleindicator.CircleIndicator3

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var adapter: OnBoardingAdapter
    private lateinit var pref:Pref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref= Pref(requireContext())
        adapter = OnBoardingAdapter {
            pref.saveShowBoarding(true)
            findNavController().navigateUp()
        }
        binding.pager.adapter = adapter
        val indicator=view.findViewById<CircleIndicator3>(R.id.circle_indicator)
        indicator.setViewPager(pager)


    }

   /* private fun onClick(view: View) {
        pref.saveShowBoarding(true)
        findNavController().navigateUp()

    }*/


}