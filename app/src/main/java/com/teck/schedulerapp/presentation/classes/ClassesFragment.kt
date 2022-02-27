package com.teck.schedulerapp.presentation.classes

import by.kirich1409.viewbindingdelegate.viewBinding
import com.teck.schedulerapp.presentation.core.BaseFragment
import com.teck.schedulerapp.R
import com.teck.schedulerapp.databinding.ClassesFragmentBinding
import com.teck.schedulerapp.domain.AppState
import com.teck.schedulerapp.presentation.core.BaseViewModel
import org.koin.core.scope.Scope

class ClassesFragment: BaseFragment<ClassesFragmentBinding>(R.layout.classes_fragment) {
    override val scope: Scope
        get() = TODO("Not yet implemented")
    override val viewBinding: ClassesFragmentBinding by viewBinding()
    override val viewModel: BaseViewModel = ClassesViewModel()

    override fun renderData(appState: AppState) {
        TODO("Not yet implemented")
    }

    companion object{
        fun newInstance() = ClassesFragment()
    }

}
