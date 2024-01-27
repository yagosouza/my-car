package com.yagosouza.meucarro.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.yagosouza.meucarro.R
import com.yagosouza.meucarro.core.extensions.observeFlow
import com.yagosouza.meucarro.core.tools.ToastHelper
import com.yagosouza.meucarro.databinding.FragmentHomeBinding
import com.yagosouza.meucarro.presentation.detail.DetailFragment
import com.yagosouza.meucarro.presentation.home.adapter.HomeAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()
    private val adapter by lazy { HomeAdapter(viewModel::onCarItemClicked) }

    private val toastHelper: ToastHelper by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        setUpUi()
        observeViewModelFlows()
        viewModel.getCars()
        setListeners()
        return binding.root
    }

    private fun setUpUi() {
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModelFlows() = with(viewModel) {
        observeFlow(state, ::onStateChanged)
        observeFlow(action, ::onAction)
    }

    private fun onStateChanged(state: HomeState) = with(state) {
        binding.progressBar.isVisible = isLoading
        cars?.let(adapter::submitList)
    }

    private fun setListeners() {
        with(binding) {
            button2.setOnClickListener { viewModel.onButtonClick() }
        }
    }

    private fun onAction(action: HomeAction) = when (action) {
        is HomeAction.ShowCarDetails -> navigateToDetail(action.id)
        is HomeAction.FinishApp -> activity?.finish()
        else -> {}
    }

    private fun navigateToDetail(id: Long) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = DetailFragment.newInstance(id)

        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}