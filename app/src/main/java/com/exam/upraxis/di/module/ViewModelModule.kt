package com.exam.upraxis.di.module

import androidx.lifecycle.ViewModel
import com.exam.upraxis.di.ViewModelKey
import com.exam.upraxis.module.users.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    internal abstract fun bindUserListViewModel(userListViewModel: UserListViewModel): ViewModel
}
