package com.exam.upraxis.common.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <M : Any, L : LiveData<M>> LifecycleOwner.observe(
  liveData: L,
  observer: (M) -> Unit = {}
) =
  liveData.observe(this, Observer(observer))

fun <M : Any, L : LiveData<M>> Fragment.observe(
  liveData: L,
  observer: (M) -> Unit = {}
) =
  liveData.observe(this.viewLifecycleOwner, Observer(observer))
