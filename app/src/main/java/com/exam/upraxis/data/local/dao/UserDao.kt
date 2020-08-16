package com.exam.upraxis.data.local.dao

import com.exam.upraxis.data.local.dto.UserDto
import com.exam.upraxis.data.local.util.RealmHelper
import com.exam.upraxis.data.local.util.findAllObservable
import com.exam.upraxis.data.local.util.saveToRealm
import com.exam.upraxis.domain.exception.QueryCascadeException
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import javax.inject.Inject

class UserDao @Inject constructor() {

  fun get(
    username: String
  ): Observable<Observable<UserDto>> = RealmHelper.rxTransaction { realm ->
    Observable.just(realm.where<UserDto>()
      .equalTo(UserDto::username.name, username)
      .findFirst() ?: throw QueryCascadeException())
  }.toObservable()

  fun getAll(realm: Realm): Observable<List<UserDto>> =
    realm.where<UserDto>()
      .sort(UserDto::firstName.name, Sort.ASCENDING)
      .findAllObservable()


  fun saveAll(
    data: List<UserDto>
  ): Single<List<UserDto>> = RealmHelper.rxTransaction { realm ->
    realm.where<UserDto>()
      .findAll()
      .deleteAllFromRealm()
    realm.saveToRealm(data)
  }

  fun save(
    data: UserDto
  ): Single<UserDto> = RealmHelper.rxTransaction { realm ->
    val oldData: UserDto? = realm.where<UserDto>()
      .equalTo(UserDto::username.name, data.username)
      .findFirst()
    oldData?.deleteFromRealm()
    realm.saveToRealm(data)
  }

}