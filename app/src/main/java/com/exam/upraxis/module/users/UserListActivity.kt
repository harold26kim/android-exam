package com.exam.upraxis.module.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.upraxis.R
import com.exam.upraxis.common.ext.observe
import com.exam.upraxis.common.ext.withBinding
import com.exam.upraxis.common.ext.withViewModel
import com.exam.upraxis.databinding.ActivityUserListBinding
import com.exam.upraxis.di.AppViewModelFactory
import com.exam.upraxis.domain.manager.ErrorHandler
import com.exam.upraxis.domain.model.User
import com.exam.upraxis.module.adapter.UserListItemAdapter
import com.exam.upraxis.module.details.UserDetailsActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UserListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    @Inject
    lateinit var errorHandler: ErrorHandler<AppCompatActivity>

    private lateinit var viewModel: UserListViewModel
    private lateinit var binding: ActivityUserListBinding

    private val adapterItems = UserListItemAdapter(::onItemClick)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = withBinding(R.layout.activity_user_list)
        viewModel = withViewModel(this, viewModelFactory){
            doGetUsers()
            observe(userList, ::onSuccess)
            observe(error, ::onError)
            observe(loading, ::onLoading)
        }

        binding.apply {
            recyclerUser.apply {
                adapter = adapterItems
                layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false
                )
            }
        }
    }

    private fun onItemClick(user: User){
        UserDetailsActivity.startActivity(
            this,
            user
        )
    }

    private fun onSuccess(userList: List<User>){
        adapterItems.submitList(userList)
    }

    private fun onError(throwable: Throwable){
        throwable.printStackTrace()
        errorHandler.handle(this, throwable)
    }

    private fun onLoading(isLoading: Boolean){
        binding.apply {
            spinkitLoading.visibility = if ( isLoading ) View.VISIBLE else View.GONE
        }
    }
}