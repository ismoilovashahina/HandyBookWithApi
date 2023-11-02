package com.example.handybookwapi.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.handybookwapi.R
import com.example.handybookwapi.databinding.FragmentSplashBinding
import com.example.handybookwapi.util.SharedPrefHelper


class SplashFragment : Fragment() {
    private lateinit var binding : FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            val shared = SharedPrefHelper.getInstance(requireContext())
            if (shared.getUser() == null) findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            else findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }, 1000)

        return binding.root
    }

}