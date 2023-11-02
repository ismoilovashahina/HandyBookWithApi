package com.example.handybookwapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.handybookwapi.R
import com.example.handybookwapi.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding.welcomeLoginMb.setOnClickListener { findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment) }
        binding.welcomeRegisterMb.setOnClickListener { findNavController().navigate(R.id.action_welcomeFragment_to_signUpFragment) }
        return binding.root
    }
}