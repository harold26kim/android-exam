package com.exam.upraxis.data.repository

import com.exam.upraxis.domain.base.LocalSource
import com.exam.upraxis.domain.base.RemoteSource
import com.exam.upraxis.domain.base.Repository
import com.exam.upraxis.domain.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @param Data The data type of the result
 */
open class SimpleRepository<Data>(
  private val localSource: LocalSource<Data>,
  private val remoteSource: RemoteSource<Data>,
  private val schedulerProvider: SchedulerProvider
) : Repository<Data>() {

  override fun query(): Observable<Data> = Observable
      .defer { localSource.get() }
      .subscribeOn(schedulerProvider.ui())

  override fun fetch(): Single<Data> = remoteSource.fetch()
      .subscribeOn(schedulerProvider.io())

  override fun save(
    data: Data
  ): Single<Data> = localSource.save(data)
      .subscribeOn(schedulerProvider.io())

}