package com.teck.schedulerapp.presentation.main

import androidx.lifecycle.ViewModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.movieapp.mv_ted.presentation.core.BaseFragment
import com.movieapp.mv_ted.presentation.core.BaseViewModel
import com.teck.schedulerapp.R
import com.teck.schedulerapp.databinding.MainFragmentLayoutBinding
import com.teck.schedulerapp.domain.AppState
import org.koin.core.scope.Scope

class MainFragment: BaseFragment<MainFragmentLayoutBinding>(R.layout.main_fragment_layout) {
    override val scope: Scope
        get() = TODO("Not yet implemented")
    override val viewBinding: MainFragmentLayoutBinding by viewBinding()
    override val viewModel: BaseViewModel = MainViewModel()

    override fun renderData(appState: AppState) {
        TODO("Not yet implemented")
    }

    companion object{
        fun newInstance() = MainFragment()
    }

}