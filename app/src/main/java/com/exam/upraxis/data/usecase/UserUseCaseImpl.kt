package com.exam.upraxis.data.usecase

import com.exam.upraxis.domain.base.Repository
import com.exam.upraxis.domain.model.User
import com.exam.upraxis.domain.sealedclass.DataResult
import com.exam.upraxis.domain.usecase.UserUseCase
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
  private val userListRepository: Repository<List<User>>
) : UserUseCase {

  override fun getUsers(): Observable<DataResult<List<User>>> =
    userListRepository.get()
      .map<DataResult<List<User>>> {
        Timber.i("All Users: ${it.map { it.username }}")
        DataResult.Success(it)
      }
      .onErrorReturn { DataResult.Failed(it) }

}