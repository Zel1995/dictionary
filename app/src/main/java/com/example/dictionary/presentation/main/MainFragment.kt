package com.example.dictionary.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.R
import com.example.dictionary.data.AppState
import com.example.dictionary.data.datasource.DataSourceLocal
import com.example.dictionary.data.datasource.DataSourceRemote
import com.example.dictionary.data.mappers.ResponseMapper
import com.example.dictionary.data.network.RetrofitImplementation
import com.example.dictionary.data.repository.RepositoryImpl
import com.example.dictionary.databinding.FragmentMainBinding
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.usecases.Interactor
import com.example.dictionary.domain.usecases.MainInteractor
import com.example.dictionary.presentation.main.SearchFragment.Companion.SEARCH_FRAGMENT_TAG
import com.example.dictionary.presentation.main.adapter.SearchAdapter
import com.example.dictionary.presentation.main.contract.MvpView

class MainFragment : Fragment(R.layout.fragment_main), MvpView {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            MainInteractor(
                RepositoryImpl(DataSourceRemote(RetrofitImplementation(), ResponseMapper())),
                RepositoryImpl(DataSourceLocal())
            )
        )
    }
    private val adapter = SearchAdapter {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        initRecyclerView()
        initSearchFragment()
        initViewModel()
    }

    private fun initViewModel(){
        viewModel.subscribe().observe(viewLifecycleOwner,{renderData(it)})
    }
    private fun initRecyclerView() {
        binding.wordsRv.adapter = adapter
    }

    private fun initSearchFragment() {
        binding.searchFab.setOnClickListener {
            val searchFragment = SearchFragment()
            searchFragment.setOnSearchClickListener { viewModel.loadData(it, true) }
            searchFragment.show(requireActivity().supportFragmentManager, SEARCH_FRAGMENT_TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun renderData(appState: AppState<List<DataModel>>) {
        when (appState) {
            is AppState.Success -> {
                adapter.setData(appState.value)
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
            }
        }
    }
}

class MainViewModelFactory(private val interactor: Interactor<AppState<List<DataModel>>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(interactor) as T
    }
}