package com.exam.upraxis.manager

import androidx.appcompat.app.AppCompatActivity
import com.exam.upraxis.domain.manager.ErrorHandler
import timber.log.Timber

class MultiErrorHandler : ErrorHandler<AppCompatActivity> {

  private val handlers = mutableListOf<ErrorHandler<AppCompatActivity>>()

  fun add(handler: ErrorHandler<AppCompatActivity>) {
    handlers.add(handler)
  }

  fun clear() {
    handlers.clear()
  }

  override fun canHandle(error: Throwable): Boolean = true

  override fun handle(
    t: AppCompatActivity,
    error: Throwable
  ) {
    Timber.e(error)
    var isHandled = false
    handlers.forEach {
      if (!isHandled && it.canHandle(error)) {
        isHandled = true
        it.handle(t, error)
      }
    }
  }
}