package com.example.myapplication.ui.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.ui.BaseFragment
import com.example.myapplication.ui.listfragment.model.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment: BaseFragment<FragmentListBinding>() {

    private val listViewModel: ListViewModel by viewModels()
    private lateinit var adapter: DataListAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListBinding
        get() = FragmentListBinding::inflate

    override fun initFragment(savedInstanceState: Bundle?) {
        initRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        with(listViewModel){
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    uiState.collect{state ->
                        when(state) {
                            is UiState.Success -> {
                                adapter.submitList(state.data)
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
    private fun initRecyclerView(){
        adapter = DataListAdapter(listViewModel.getEventClickListener())
        binding.dataList.adapter = adapter
    }
}