package com.teck.schedulerapp.presentation.main.adapters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.teck.schedulerapp.data.model.Classes
import com.teck.schedulerapp.data.model.Lesson
import com.teck.schedulerapp.databinding.ItemClassBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class LessonsAdapter(
    private val data: Classes,
    private val duration: Long,
    private val formatter: DateTimeFormatter
) :
    RecyclerView.Adapter<LessonsAdapter.LessonItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonItem = LessonItem(
        ItemClassBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: LessonItem, position: Int) {
        holder.bind(data.lessons[position])
    }

    override fun getItemCount(): Int = data.lessons.size

    inner class LessonItem(private val viewBinding: ItemClassBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(lesson: Lesson) {
            val dateTime = LocalDateTime.parse(lesson.dateStart, formatter)
            viewBinding.titleOfLesson.text = lesson.title
            viewBinding.timeOfLesson.text =
                "${dateTime.toLocalTime()} - ${dateTime.toLocalTime().plusMinutes(duration)}"
            viewBinding.lessonImage.setImageResource(lesson.image)
            initListeners()
        }

        private fun initListeners() {
            viewBinding.skypeItem.setOnClickListener {
                try {
                    viewBinding.root.context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=Skype")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    viewBinding.root.context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                "https://play.google.com/store/apps/details?id=Skype"
                            )
                        )
                    )
                }
            }
        }
    }
}