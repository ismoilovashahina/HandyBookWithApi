package com.example.handybookwapi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.handybookwapi.R
import com.example.handybookwapi.databinding.FragmentSignUpBinding
import com.example.handybookwapi.model.SignUp
import com.example.handybookwapi.model.User
import com.example.handybookwapi.networking.APIClient
import com.example.handybookwapi.networking.APIService
import com.example.handybookwapi.util.SharedPrefHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val api = APIClient.getInstance().create(APIService::class.java)
    private lateinit var shared: SharedPrefHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        shared = SharedPrefHelper.getInstance(requireContext())

        setbackButton()
        setSignUpButton()



        return binding.root
    }

    private fun setbackButton() {
        binding.signupBackButtonIv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setSignUpButton() {
        binding.signupSignupMb.setOnClickListener {
            if (binding.signupUsernameEditAcet.text.toString() == "") {
                showToast("Username bo'sh bo'lishi mumkin emas")
                return@setOnClickListener
            }
            if (binding.signupPasswordEditAcet.text.toString() == "") {
                showToast("Parol bo'sh bo'lishi mumkin emas")
                return@setOnClickListener
            }
            val signUp = SignUp(
                username = binding.signupUsernameEditAcet.text.toString().trim(),
                fullname = binding.signupFirstnameAcet.text.toString()
                    .trim() + " " + binding.signupSurnameEditAcet.text.toString().trim(),
                password = binding.signupPasswordEditAcet.text.toString().trim(),
                email = binding.signupEmailEditAcet.text.toString()
            )
            if (!validate(signUp)) return@setOnClickListener

            api.signup(signUp).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.d("TAG", "$response")
                    if (response.code() == 422){
                        showToast("Username allaqachon olingan")
                        binding.signupUsernameEditAcet.setText("")
                        return
                    }
                    if (!response.isSuccessful) {
                        showToast("sign up error")
                        return
                    }
                    val user = response.body()!!
                    shared.setUser(user)
                    findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("TAG", "$t")
                }
            })
        }
    }

    private fun validate(signUp: SignUp): Boolean {
        var out = true
        if (binding.signupFirstnameAcet.text.toString() == "") {
            binding.signupFirstnameIncorrectTv.visibility = View.VISIBLE
            out = false
        } else binding.signupFirstnameIncorrectTv.visibility = View.GONE

        if (binding.signupSurnameEditAcet.text.toString() == "") {
            binding.signupSurnameIncorrectTv.visibility = View.VISIBLE
            out = false
        } else binding.signupSurnameIncorrectTv.visibility = View.GONE

        if (signUp.username.length < 5) {
            binding.signupUsernameIncorrectTv.visibility = View.VISIBLE
            out = false
        } else binding.signupUsernameIncorrectTv.visibility = View.GONE

        if (!signUp.email.contains("@")) {
            binding.signupEmailIncorrectTv.visibility = View.VISIBLE
            out = false
        } else binding.signupEmailIncorrectTv.visibility = View.GONE

        if (signUp.password.length < 8) {
            binding.signupPasswordIncorrectTv.visibility = View.VISIBLE
            binding.signupPasswordEditAcet.setText("")
            binding.signupReenterPasswordEditAcet.setText("")
            out = false
        } else binding.signupPasswordIncorrectTv.visibility = View.GONE

        if (signUp.password.length >= 8 && binding.signupReenterPasswordEditAcet.text.toString()
                .trim() != signUp.password
        ) {
            binding.signupReenterPasswordIncorrectTv.visibility = View.VISIBLE
            binding.signupReenterPasswordEditAcet.setText("")
            out = false
        } else binding.signupReenterPasswordIncorrectTv.visibility = View.GONE
        return out
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}