package com.exam.upraxis.di.module

import com.exam.upraxis.module.details.UserDetailsActivity
import com.exam.upraxis.module.users.UserListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun userListActivity(): UserListActivity

    @ContributesAndroidInjector
    internal abstract fun userDetailsActivity(): UserDetailsActivity
}