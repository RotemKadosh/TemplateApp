package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment< Binding : ViewDataBinding> : Fragment(){

    private var _binding: Binding? = null
    val binding get() = _binding ?: throw IllegalStateException("Cannot access view after view destroyed and before view creation")
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = bindingInflater.invoke(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initFragment(savedInstanceState)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initFragment(savedInstanceState: Bundle?)
}