package com.exam.upraxis.manager

import androidx.appcompat.app.AppCompatActivity
import com.exam.upraxis.common.ext.showToast
import com.exam.upraxis.domain.manager.ErrorHandler
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException

class ApiErrorHandler(private val gson: Gson) : ErrorHandler<AppCompatActivity> {

  override fun canHandle(error: Throwable): Boolean =
    error is HttpException && error.code() in 400..499 && error.code() != 401

  override fun handle(
    t: AppCompatActivity,
    error: Throwable
  ) {
    val httpException = error as HttpException
    val response = httpException.response()
    try {
      t.applicationContext.showToast("An API Error has occurred")
    } catch (e: IOException) {
      e.printStackTrace()
    } catch (e: JsonSyntaxException) {
      e.printStackTrace()
    }
  }

}