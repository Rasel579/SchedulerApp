package com.teck.schedulerapp.presentation.classes.adapter.viewholders

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.teck.schedulerapp.data.model.Lesson
import com.teck.schedulerapp.databinding.ItemLessonBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ClassesItemUsual(
    private val viewBinding: ItemLessonBinding,
    private val formatter: DateTimeFormatter,
    private val duration: Long) : RecyclerView.ViewHolder(viewBinding.root) {
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