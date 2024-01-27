package com.yagosouza.meucarro.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.yagosouza.meucarro.core.extensions.observeFlow
import com.yagosouza.meucarro.core.tools.ToastHelper
import com.yagosouza.meucarro.databinding.FragmentDetailBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment(private val id: Long) : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    private val viewModel: DetailViewModel by viewModel {
        parametersOf(id)
    }

    private val toastHelper: ToastHelper by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        observeViewModelFlows()
        viewModel.getDetail()
        setListeners()
        return binding.root
    }

    private fun observeViewModelFlows() = with(viewModel) {
        observeFlow(state, ::onStateChanged)
        observeFlow(action, ::onAction)
    }

    private fun onStateChanged(state: DetailState) = with(state) {
        binding.progressBar.isVisible = isLoading
        binding.textViewCarName.text= carDetail?.description
    }

    private fun setListeners() {
        with(binding) {
            button2.setOnClickListener { viewModel.onButtonClick() }
        }
    }

    private fun onAction(action: DetailAction) = when (action) {
        is DetailAction.ShowToastDescripton -> toastHelper.showToast(action.description)
        is DetailAction.NavigateToHome -> fragmentManager?.popBackStack()
        else -> {}
    }

    companion object {
        fun newInstance(id: Long) = DetailFragment(id)
    }

}