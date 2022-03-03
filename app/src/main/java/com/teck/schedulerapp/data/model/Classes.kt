package com.teck.schedulerapp.data.model

data class Classes(
    val title: String,
    val lessons: List<Lesson>,
    val homeworks: List<HomeWork>
)
