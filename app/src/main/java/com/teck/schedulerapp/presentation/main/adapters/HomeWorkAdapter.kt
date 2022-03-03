package com.teck.schedulerapp.presentation.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teck.schedulerapp.data.model.Classes
import com.teck.schedulerapp.data.model.HomeWork
import com.teck.schedulerapp.databinding.ItemHomeworkBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeWorkAdapter(
    private val data: Classes,
    private val formatter: DateTimeFormatter,
    private  val nowDate: LocalDateTime
) :
    RecyclerView.Adapter<HomeWorkAdapter.HomeWorkItem>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWorkItem = HomeWorkItem(
        ItemHomeworkBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: HomeWorkItem, position: Int) {
        holder.bind(data.homeworks[position])
    }

    override fun getItemCount(): Int = data.homeworks.size

    inner class HomeWorkItem(private val viewBinding: ItemHomeworkBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(homeWork: HomeWork) {
            val dateTime = LocalDateTime.parse(homeWork.dateStart, formatter)
            val leftTime =  dateTime.dayOfYear - nowDate.dayOfYear

            viewBinding.titleOfHomework.text = homeWork.title
            viewBinding.leftTime.text = "${leftTime} days left"
            viewBinding.image.setImageResource(homeWork.image)
            viewBinding.homeworkTask.text = homeWork.task
        }
    }
}