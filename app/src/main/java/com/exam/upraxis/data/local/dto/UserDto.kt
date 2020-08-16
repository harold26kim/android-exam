package com.exam.upraxis.data.local.dto

import com.exam.upraxis.domain.exception.QueryCascadeException
import com.exam.upraxis.domain.model.User
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class UserDto() : RealmObject() {
  @PrimaryKey
  var username: String? = null
  var title: String? = null
  var firstName: String? = null
  var lastLame: String? = null
  var email: String? = null
  var phoneNumber: String? = null
  var gender: String? = null
  var birthdate: Long? = null
  var location: LocationDto? = null

  constructor(item: User) : this(){
    this.username = item.username
    this.title = item.title
    this.firstName = item.firstName
    this.lastLame = item.lastName
    this.email = item.email
    this.phoneNumber = item.phoneNo
    this.gender = item.gender
    this.birthdate = item.birthdate
    this.location = LocationDto(
      item = item.location ?: throw QueryCascadeException(),
      username = item.username ?: throw QueryCascadeException(),
      generatedId = UUID.randomUUID().toString()
    )
  }
  val toUser: User
    get() = User(
      username = username,
      title = title,
      firstName = firstName,
      lastName = lastLame,
      email = email,
      phoneNo = phoneNumber,
      gender = gender,
      birthdate = birthdate,
      location = location?.toLocation ?: throw QueryCascadeException()
    )
}