package com.cascer.loginsampleapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cascer.loginsampleapp.data.Resource
import com.cascer.loginsampleapp.databinding.ActivityLoginBinding
import com.cascer.loginsampleapp.ui.user.UserActivity
import com.cascer.loginsampleapp.utils.gone
import com.cascer.loginsampleapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            viewModel.getToken.observe(this@LoginActivity) {
                if (it.isNotEmpty()) {
                    startActivity(Intent(this@LoginActivity, UserActivity::class.java))
                    finish()
                }
            }
            btnLogin.setOnClickListener {
                requestLogin()
            }
        }
    }

    private fun requestLogin() {
        viewModel.login(
            email = binding.etEmail.text.toString(), password = binding.etPassword.text.toString()
        ).observe(this@LoginActivity) {
            if (it != null) {
                when (it) {
                    is Resource.Success -> {
                        binding.progressbar.gone()
                        if (it.data?.isNotEmpty() == true) viewModel.saveToken(it.data.toString())
                    }

                    is Resource.Loading -> {
                        binding.progressbar.visible()
                    }

                    is Resource.Error -> {
                        binding.progressbar.gone()
                        Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}