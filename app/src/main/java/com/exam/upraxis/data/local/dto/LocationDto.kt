package com.exam.upraxis.data.local.dto

import com.exam.upraxis.domain.model.Location
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class LocationDto() : RealmObject() {
  @PrimaryKey
  var id: String = ""
  var username: String? = ""
  var street: String? = null
  var city: String? = null
  var state: String? = null
  var postcode: String? = null


  constructor(item: Location, username: String, generatedId: String) : this() {
    this.id = generatedId
    this.username = username
    this.street = item.street
    this.city = item.city
    this.state = item.state
    this.postcode = item.postcode
  }

  val toLocation: Location
    get() = Location(
      state = this.state,
      street = this.street,
      city = this.city,
      postcode = this.postcode
    )
}