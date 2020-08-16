package com.exam.upraxis.manager

import androidx.appcompat.app.AppCompatActivity
import com.exam.upraxis.common.ext.showToast
import com.exam.upraxis.domain.exception.DefaultUserReadableException
import com.exam.upraxis.domain.manager.ErrorHandler
import io.reactivex.exceptions.CompositeException

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
class DefaultErrorHandler(
  private val compositeErrorHandler: ErrorHandler<AppCompatActivity>,
  private val buildType: String
) : ErrorHandler<AppCompatActivity> {

  private val defaultMessage = "Something went wrong. Please try again…"

  override fun canHandle(error: Throwable): Boolean = true

  override fun handle(
    activity: AppCompatActivity,
    error: Throwable
  ) = when (error) {
    is CompositeException -> error.exceptions.forEach {
      compositeErrorHandler.handle(activity, it)
    }
    is DefaultUserReadableException -> activity.applicationContext
        .showToast(error.message ?: defaultMessage)
    else -> activity.applicationContext
      .showToast(error.message ?: defaultMessage)
  }

}