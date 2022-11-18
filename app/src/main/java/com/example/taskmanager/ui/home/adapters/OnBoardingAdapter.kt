package com.example.taskmanager.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.data.models.OnBoarding

class OnBoardingAdapter(
    val onClick: () -> Unit
) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val arrayList = arrayListOf(
        OnBoarding(
            "https://www.nicepng.com/png/full/190-1906200_hello-png.png",
            "It's Task Manager"
        ),
        OnBoarding(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFgUQqLyqPl08jRUSOCGvE8x7cm5PkaANrJg&usqp=CAU",
            "Edit",
            "Нажмите на Edit, чтобы добавить Task"
        ),
        OnBoarding(
            "https://i.stack.imgur.com/pAoZ5.png",
            "EditText",
            "Заполните обьязательные поля"
        ),
        OnBoarding(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQu_BoBAwc-CqY-cuGDIQsTe3r-bQj95DICag&usqp=CAU",
            "Пользуйтесь на здоровье"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int = arrayList.size

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {
           binding.textSkip.setOnClickListener {
                onClick()
            }
            binding.btnStart.setOnClickListener {
                onClick()
            }
            binding.btnStart.isVisible = adapterPosition == arrayList.lastIndex
            binding.textSkip.isVisible = adapterPosition != arrayList.lastIndex
            binding.titleBoarding.text = onBoarding.title
            binding.textBoarding.text = onBoarding.text
            Glide.with(binding.imgBoarding).load(onBoarding.image).into(binding.imgBoarding)

        }
    }
}