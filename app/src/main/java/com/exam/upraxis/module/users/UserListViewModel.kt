package com.exam.upraxis.module.users

import android.app.Application
import com.exam.upraxis.common.base.BaseViewModel
import com.exam.upraxis.domain.common.SingleLiveEvent
import com.exam.upraxis.domain.model.User
import com.exam.upraxis.domain.rx.SchedulerProvider
import com.exam.upraxis.domain.sealedclass.DataResult
import com.exam.upraxis.domain.usecase.UserUseCase
import javax.inject.Inject

class UserListViewModel @Inject constructor(
  application: Application,
  private val userUseCase: UserUseCase,
  private val schedulerProvider: SchedulerProvider): BaseViewModel(application) {

  val loading = SingleLiveEvent<Boolean>()
  val userList = SingleLiveEvent<List<User>>()
  val error = SingleLiveEvent<Throwable>()

  fun doGetUsers() {
    compositeDisposable.add(
      userUseCase.getUsers()
        .map {
          loading.postValue(false)
          when (it) {
            is DataResult.Success -> userList.postValue(it.value)
            is DataResult.Failed -> error.postValue(it.error)
          }
        }
        .startWith(listOf(loading.postValue(true)))
        .onErrorReturn { error.postValue(it) }
        .subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
        .subscribe()
    )
  }
}