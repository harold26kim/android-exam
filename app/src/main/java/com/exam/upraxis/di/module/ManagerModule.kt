package com.exam.upraxis.di.module

import androidx.appcompat.app.AppCompatActivity
import com.exam.upraxis.domain.manager.ErrorHandler
import com.exam.upraxis.manager.ApiErrorHandler
import com.exam.upraxis.manager.AppSchedulerProvider
import com.exam.upraxis.domain.rx.SchedulerProvider
import com.exam.upraxis.manager.DefaultErrorHandler
import com.exam.upraxis.manager.MultiErrorHandler
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
class ManagerModule {

  @Provides
  @Singleton
  fun providesSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

  @Singleton
  @Provides
  fun appDebugTree(): Timber.Tree = Timber.DebugTree()

  @Singleton
  @Provides
  fun errorHandler(gson: Gson): ErrorHandler<AppCompatActivity> {
    val multiErrorHandler =
      MultiErrorHandler()
    multiErrorHandler.add(
        ApiErrorHandler(gson)
    )
    // should be last
    multiErrorHandler.add(
        DefaultErrorHandler(
            multiErrorHandler, "Debug"
        )
    )
    return multiErrorHandler
  }


}