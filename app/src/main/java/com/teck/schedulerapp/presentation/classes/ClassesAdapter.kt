package com.teck.schedulerapp.presentation.classes

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teck.schedulerapp.data.model.Classes
import com.teck.schedulerapp.data.model.Lesson
import com.teck.schedulerapp.databinding.ItemClassBinding
import com.teck.schedulerapp.databinding.ItemLessonBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ClassesAdapter(
    private val data: Classes,
    private val duration: Long,
    private val formatter: DateTimeFormatter
) : RecyclerView.Adapter<ClassesAdapter.ClassesItem>() {
    init {
        data.lessons =  data.lessons.sortedBy { it.dateStart }
        Log.e("data", data.lessons.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesItem = ClassesItem(
        ItemLessonBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: ClassesItem, position: Int) {
        holder.bind(data.lessons[position])
    }

    override fun getItemCount(): Int = data.lessons.size

    inner class ClassesItem(private val viewBinding: ItemLessonBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(lesson: Lesson) {
            val dateTime = LocalDateTime.parse(lesson.dateStart, formatter)
            viewBinding.titleOfLesson.text = lesson.title
            viewBinding.timeOfLesson.text =
                "${dateTime.toLocalTime()} - ${dateTime.toLocalTime().plusMinutes(duration)}"
            viewBinding.lessonImage.setImageResource(lesson.image)
            viewBinding.time.text = lesson.dateStart
            initListeners()
        }

        private fun initListeners() {
            viewBinding.skypeItem.setOnClickListener {
                val skypeIntent = Intent("android.intent.action.VIEW")
                skypeIntent.data = Uri.parse("skype:skypeusername")
                viewBinding.root.context.startActivity(skypeIntent)
            }
        }
    }
}