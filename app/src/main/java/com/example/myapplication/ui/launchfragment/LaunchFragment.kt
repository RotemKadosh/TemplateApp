package com.example.myapplication.ui.launchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentLaunchBinding
import com.example.myapplication.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchFragment: BaseFragment<FragmentLaunchBinding>() {

    private val launchViewModel : LaunchViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLaunchBinding
        get() = FragmentLaunchBinding::inflate

    override fun initFragment(savedInstanceState: Bundle?) {
        initObservers()
        launchViewModel.onLaunchStart()
    }

    private fun initObservers() {
        launchViewModel.launchScreenEnded.observe(viewLifecycleOwner){
            navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() {
        val action = LaunchFragmentDirections.actionLaunchFragmentToListFragment()
        findNavController().navigate(action)
    }
}
