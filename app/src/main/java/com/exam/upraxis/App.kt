package com.exam.upraxis

import androidx.lifecycle.ProcessLifecycleOwner
import com.exam.upraxis.di.DaggerAppComponent
import com.exam.upraxis.domain.usecase.AppInitializationUseCase
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var appInitializationUseCase: AppInitializationUseCase

    @Inject
    lateinit var lifecycleListener: AppLifecycleListener

    @Inject
    lateinit var debugTree: Timber.Tree

    override fun onCreate() {
        super.onCreate()

        Timber.plant(debugTree)

        appInitializationUseCase.init()

        ProcessLifecycleOwner.get()
            .lifecycle.addObserver(lifecycleListener)

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}