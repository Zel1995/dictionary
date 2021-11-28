package com.example.dictionary.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dictionary.databinding.DialogFragmentSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import java.util.concurrent.TimeUnit

class SearchFragment : BottomSheetDialogFragment() {

    companion object {
        const val SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT_TAG"
    }

    private var _binding: DialogFragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: ((word: String) -> Unit)? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInput()
    }

    private fun initInput() {
        compositeDisposable += RxTextView.textChanges(binding.searchInput)
            .debounce(1, TimeUnit.SECONDS)
            .filter { it.length > 1 }
            .subscribe {
                if(!it.isNullOrEmpty() && it != ""){
                    onSearchClickListener?.invoke(it.toString())
                }

            }
    }

    fun setOnSearchClickListener(listener: (word: String) -> Unit) {
        onSearchClickListener = listener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
        onSearchClickListener = null
    }

}