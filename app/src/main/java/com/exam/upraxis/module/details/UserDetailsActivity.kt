package com.exam.upraxis.module.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.exam.upraxis.R
import com.exam.upraxis.common.ext.withBinding
import com.exam.upraxis.databinding.ActivityUserDetailsBinding
import com.exam.upraxis.domain.model.User
import dagger.android.support.DaggerAppCompatActivity

class UserDetailsActivity : DaggerAppCompatActivity() {

    companion object {
        private const val EXTRAS_USER   = "USER"
        fun startActivity(context: Context, user: User) {
            context.startActivity(
                Intent(context, UserDetailsActivity::class.java).also {
                    it.putExtra(EXTRAS_USER, user)
                }
            )
        }
    }

    private lateinit var binding: ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = withBinding(R.layout.activity_user_details)

        binding.apply {
            user = intent.getParcelableExtra(EXTRAS_USER)
            imageBack.setOnClickListener { onBackPressed() }
        }

    }
}