package com.teck.schedulerapp.presentation.classes

import by.kirich1409.viewbindingdelegate.viewBinding
import com.teck.schedulerapp.R
import com.teck.schedulerapp.data.datasource.MockDataSourceImpl
import com.teck.schedulerapp.data.model.Classes
import com.teck.schedulerapp.data.repository.RepositoryImpl
import com.teck.schedulerapp.databinding.ClassesFragmentBinding
import com.teck.schedulerapp.domain.AppState
import com.teck.schedulerapp.presentation.core.BaseFragment
import com.teck.schedulerapp.presentation.core.BaseViewModel
import org.koin.core.scope.Scope
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ClassesFragment : BaseFragment<ClassesFragmentBinding>(R.layout.classes_fragment) {
    override val scope: Scope
        get() = TODO("Not yet implemented")
    override val viewBinding: ClassesFragmentBinding by viewBinding()
    override val viewModel: BaseViewModel = ClassesViewModel(RepositoryImpl(MockDataSourceImpl()))
    private val formatter by lazy { DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm") }
    private val nowDate by lazy { LocalDateTime.now() }
    private val durationOfTime = 45L

    override fun onStart() {
        super.onStart()
        viewModel.getLiveData().observe(viewLifecycleOwner){renderData(it)}
        viewModel.getData()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data is Classes) {
                    val adapterLesson = ClassesAdapter(appState.data, durationOfTime, formatter)
                    viewBinding.classesRecycleView.adapter = adapterLesson
                }
            }
        }
    }

    companion object {
        fun newInstance() = ClassesFragment()
    }

}
