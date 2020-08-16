package com.exam.upraxis.domain.base

import io.reactivex.Observable
import io.reactivex.Single

interface LocalSource<Data> {
  fun get(): Observable<Data>
  fun save(
    data: Data
  ): Single<Data>
}