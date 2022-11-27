package com.example.taskmanager.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.data.models.OnBoarding

class OnBoardingAdapter(
    val onClick: () -> Unit
) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val arrayList = arrayListOf(
        OnBoarding(
            R.raw.task3,
            "It's Task Manager"
        ),
        OnBoarding(
            R.raw.boarding_edit,
            "Edit",
            "Нажмите на Edit, чтобы добавить Task"
        ),
        OnBoarding(
            R.raw.boarding_field,
            "EditText",
            "Заполните обьязательные поля"
        ),
        OnBoarding(
            R.raw.boarding_save, "Кнопка Save",
            "И сохраните нажав на кнопку Save"
        ),
        OnBoarding(
            R.raw.boarding_luck,
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
            onBoarding.image?.let { binding.lottieOnBoarding.setAnimation(it) }
        }
    }
}



