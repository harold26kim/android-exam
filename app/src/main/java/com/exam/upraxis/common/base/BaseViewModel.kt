package com.exam.upraxis.common.base

import android.app.Application
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        compositeDisposable.clear()
    }
}