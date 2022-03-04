package com.teck.schedulerapp.presentation.main

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teck.schedulerapp.R
import com.teck.schedulerapp.data.datasource.MockDataSourceImpl
import com.teck.schedulerapp.data.model.Classes
import com.teck.schedulerapp.data.repository.RepositoryImpl
import com.teck.schedulerapp.databinding.MainFragmentLayoutBinding
import com.teck.schedulerapp.domain.AppState
import com.teck.schedulerapp.presentation.core.BaseFragment
import com.teck.schedulerapp.presentation.core.BaseViewModel
import com.teck.schedulerapp.presentation.main.adapters.HomeWorkAdapter
import com.teck.schedulerapp.presentation.main.adapters.LessonsAdapter
import org.koin.core.scope.Scope
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class MainFragment : BaseFragment<MainFragmentLayoutBinding>(R.layout.main_fragment_layout) {
    override val scope: Scope
        get() = TODO("Not yet implemented")
    override val viewBinding: MainFragmentLayoutBinding by viewBinding()
    override val viewModel: BaseViewModel = MainViewModel(RepositoryImpl(MockDataSourceImpl()))
    private val formatter by lazy { DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm") }
    private val nowDate  by lazy { LocalDateTime.now() }
    private val durationOfTime = 45L

    override fun onStart() {
        super.onStart()
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it as AppState) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getData()
    }



    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data is Classes) {
                    val adapterLesson = LessonsAdapter(appState.data, durationOfTime, formatter)
                    val adapterHomeWork = HomeWorkAdapter(appState.data, formatter, nowDate)
                    viewBinding.classesRecycleView.adapter = adapterLesson
                    viewBinding.homeworkRecycleView.adapter = adapterHomeWork
                    addLessenWatcher(appState.data)
                }
            }
        }
    }

    private fun addLessenWatcher(data: Classes) {
        val dateTime = LocalDateTime.parse(data.lessons.first().dateStart, formatter)
        val different= dateTime.toEpochSecond(ZoneOffset.MAX) - nowDate.toEpochSecond(ZoneOffset.MAX)

        object : CountDownTimer(different, COUNT_DOWN_LATCH){
            override fun onTick(millisUntilFinished: Long) {
                val tick = millisUntilFinished
                val dayDecimal =  (tick/SECONDS_IN_SHIFT)/DECIMAL
                val day = (tick/SECONDS_IN_SHIFT)%DECIMAL
                val diffHour = tick%SECONDS_IN_SHIFT
                val hoursDecimal = (diffHour/SECONDS_IN_HOUR)/DECIMAL
                val hour = (diffHour/SECONDS_IN_HOUR)%SECONDS_IN_MINUTE
                val diffMinutes = diffHour%SECONDS_IN_HOUR
                val minutesDecimal = (diffMinutes/SECONDS_IN_MINUTE)/DECIMAL
                val minutes = (diffMinutes/SECONDS_IN_MINUTE)%DECIMAL
                viewBinding.daysDecimal.text = dayDecimal.toString()
                viewBinding.days.text = day.toString()
                viewBinding.hoursDecimal.text = hoursDecimal.toString()
                viewBinding.hours.text = hour.toString()
                viewBinding.minutesDecimal.text = minutesDecimal.toString()
                viewBinding.minutes.text = minutes.toString()
            }
            override fun onFinish() {}
        }.start()
    }


    companion object {
        fun newInstance() = MainFragment()
        private const val  DECIMAL = 10
        private const val  SECONDS_IN_SHIFT = 86400
        private const val  SECONDS_IN_HOUR = 3600
        private const val  SECONDS_IN_MINUTE = 60
        private const val  COUNT_DOWN_LATCH = 1L
    }

}