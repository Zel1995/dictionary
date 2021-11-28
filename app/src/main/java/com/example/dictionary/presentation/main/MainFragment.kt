package com.example.dictionary.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dictionary.R
import com.example.dictionary.data.AppState
import com.example.dictionary.databinding.FragmentMainBinding
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.presentation.main.SearchFragment.Companion.SEARCH_FRAGMENT_TAG
import com.example.dictionary.presentation.main.adapter.SearchAdapter
import com.example.dictionary.presentation.main.contract.MvpView
import com.example.dictionary.presentation.main.contract.Presenter

class MainFragment : Fragment(R.layout.fragment_main), MvpView {
    private val presenter: Presenter<MvpView> = MainPresenterImpl()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = SearchAdapter {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        _binding = FragmentMainBinding.bind(view)
        initRecyclerView()
        initSearchFragment()
    }

    private fun initRecyclerView() {
        binding.wordsRv.adapter = adapter
    }

    private fun initSearchFragment() {
        binding.searchFab.setOnClickListener {
            val searchFragment = SearchFragment()
            searchFragment.setOnSearchClickListener { presenter.getData(it, true) }
            searchFragment.show(requireActivity().supportFragmentManager, SEARCH_FRAGMENT_TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView(this)
        _binding = null
    }

    override fun renderData(appState: AppState<List<DataModel>>) {
        when (appState) {
            is AppState.Success -> {
                adapter.setData(appState.value)
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
            }
        }
    }


}