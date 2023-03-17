package com.example.myapplication.ui.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.example.myapplication.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: BaseFragment<FragmentDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun initFragment(savedInstanceState: Bundle?) {

    }
}