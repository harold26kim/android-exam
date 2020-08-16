package com.exam.upraxis.domain.base

import io.reactivex.Single

interface RemoteSource<Data> {

  fun fetch(): Single<Data>

}