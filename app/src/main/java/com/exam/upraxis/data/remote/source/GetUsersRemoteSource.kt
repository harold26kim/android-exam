package com.exam.upraxis.data.remote.source

import com.exam.upraxis.data.remote.service.UserApiService
import com.exam.upraxis.domain.base.RemoteSource
import com.exam.upraxis.domain.model.User
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class GetUsersRemoteSource @Inject constructor(
  private val userApiService: UserApiService
) : RemoteSource<List<User>> {

    override fun fetch(): Single<List<User>> =
        userApiService.getUsers()
            .map {
                Timber.i("get: All Users: ${it.map { it.username }}")
                it.map {  repo -> repo.toUser  }
            }
}