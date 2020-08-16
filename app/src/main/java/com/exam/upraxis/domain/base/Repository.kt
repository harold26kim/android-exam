package com.exam.upraxis.domain.base


import com.exam.upraxis.domain.exception.NoRecordsFoundException
import com.exam.upraxis.domain.exception.QueryCascadeException
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.functions.Function

abstract class Repository<Data> {

    abstract fun query(): Observable<Data>

    abstract fun fetch(): Single<Data>

    abstract fun save(
        data: Data
    ): Single<Data>

    /**
     * query from local database, if empty call refresh
     */
    fun get(): Observable<Data> {
        return getLocal()
            .switchMap { forceRefreshIfEmptyObservable(it) }
    }

    /**
     * Trigger fetch data from network and save result
     */
    fun refresh(): Single<Data> = fetch()
        .flatMap { save(it) }

    private fun getLocal(): Observable<Data> = query()
      .onErrorResumeNext(errorResumeFunction())

    private fun errorResumeFunction(): Function<Throwable, ObservableSource<out Data>> =
        Function { error ->
            when {
                (error is NoRecordsFoundException ) || error is QueryCascadeException ->
                    refresh()
                    .toObservable()
                        .flatMap { getLocal() }
                else -> throw error
            }
        }
    private fun forceRefreshIfEmptyObservable( data: Data ): Observable<Data> = when {
        (data as? Collection<*>)?.isEmpty() == true -> refresh()
            .toObservable()
            .flatMap { getLocal() }
        else -> Observable.just(data)
    }
    private fun Observable<Data>.startWithDataIfNotEmpty(data: Data): Observable<Data> = when {
        (data as? Collection<*>)?.isEmpty() == true -> this
        else -> this.startWith(data)
    }
}