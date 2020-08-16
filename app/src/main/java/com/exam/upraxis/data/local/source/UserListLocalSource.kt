package com.exam.upraxis.data.local.source

import com.exam.upraxis.data.local.dao.UserDao
import com.exam.upraxis.data.local.dto.UserDto
import com.exam.upraxis.data.local.util.RealmInstance
import com.exam.upraxis.domain.base.LocalSource
import com.exam.upraxis.domain.model.User
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UserListLocalSource @Inject constructor(
  private val realmInstance: RealmInstance,
  private val userDao: UserDao
) : LocalSource<List<User>> {

  override fun get(): Observable<List<User>> =
    userDao.getAll(
      realm = realmInstance.getRealm()
    )
      .map {
        Timber.i("get: All Users: ${it.map { it.username }}")
        it.map { dto -> dto.toUser }
      }

  override fun save(
    data: List<User>
  ): Single<List<User>> =
    userDao.saveAll(
      data = data.map { UserDto(it) }
    )
      .map {
        Timber.i("saveAll: All Users: ${it.map { it.username }}")
        it.map { dto -> dto.toUser }
      }
}