package com.cascer.loginsampleapp.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.loginsampleapp.R
import com.cascer.loginsampleapp.databinding.ActivityUserBinding
import com.cascer.loginsampleapp.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private val viewModel: UserViewModel by viewModels()
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        requestData()
    }

    private fun setupViews() {
        with(binding) {
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.logout -> {
                        viewModel.clearToken()
                        startActivity(Intent(this@UserActivity, LoginActivity::class.java))
                        finish()
                        true
                    }

                    else -> false
                }
            }
            rvUser.apply {
                layoutManager =
                    LinearLayoutManager(this@UserActivity, LinearLayoutManager.VERTICAL, false)
                adapter =
                    userAdapter.withLoadStateFooter(footer = LoadingStateAdapter { userAdapter.retry() })
            }
        }
    }

    private fun requestData() {
        with(viewModel) {
            users.observe(this@UserActivity) {
                userAdapter.submitData(lifecycle, it)
            }
        }
    }
}