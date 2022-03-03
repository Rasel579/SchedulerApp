package com.teck.schedulerapp.data.datasource

import com.teck.schedulerapp.R
import com.teck.schedulerapp.data.model.Classes
import com.teck.schedulerapp.data.model.HomeWork
import com.teck.schedulerapp.data.model.Lesson

fun getMockData(): Classes = Classes(
    "First",
    lessons = listOf(
        Lesson(
            title = "History",
            dateStart = "19/03/2022 20:00",
            image = R.drawable.icons8_man_student_48
        ),
        Lesson(
            title = "Literature",
            dateStart = "18/03/2022 20:00",
            image = R.drawable.icons8_man_student_48
        ),
        Lesson(
            title = "Physics",
            dateStart = "20/03/2022 20:00",
            image = R.drawable.icons8_man_student_48
        ),
        Lesson(
            title = "History",
            dateStart = "05/03/2022 20:00",
            image = R.drawable.icons8_man_student_48
        ),
        Lesson(
            title = "Literature",
            dateStart = "05/03/2022 10:00",
            image = R.drawable.icons8_man_student_48
        ),
        Lesson(
            title = "Physics",
            dateStart = "07/03/2022 20:00",
            image = R.drawable.icons8_man_student_48
        )
    ),
    homeworks = listOf(
        HomeWork(
            title = "Physics",
            dateStart = "18/03/2022 20:00",
            image = R.drawable.icons8_man_student_48,
            task = "Do homework Physics"
        ),
        HomeWork(
            title = "Literature",
            dateStart = "19/03/2022 20:00",
            image = R.drawable.icons8_man_student_48,
            task = "Do homework Literature"
        ),
        HomeWork(
            title = "Literature",
            dateStart = "20/03/2022 20:00",
            image = R.drawable.icons8_man_student_48,
            task = "Do homework Literature"
        ),
        HomeWork(
            title = "Physics",
            dateStart = "27/03/2022 20:00",
            image = R.drawable.icons8_man_student_48,
            task = "Do homework Physics"
        ),
        HomeWork(
            title = "Literature",
            dateStart = "26/03/2022 20:00",
            image = R.drawable.icons8_man_student_48,
            task = "Do homework Literature"
        ),
        HomeWork(
            title = "Literature",
            dateStart = "25/03/2022 20:00",
            image = R.drawable.icons8_man_student_48,
            task = "Do homework Literature"
        )
    )
)