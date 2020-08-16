package com.exam.upraxis.testcases

import com.exam.upraxis.domain.model.Location
import com.exam.upraxis.domain.model.User


object TestData {

  val user1 = User(
    title = "mr",
    username = "juancruz",
    email = "juancruz@.test.com",
    firstName = "Juan",
    lastName = " Cruz",
    phoneNo = "123-4567",
    gender = "Male",
    birthdate = 32132109,
    location = Location(
      street = "dian",
      city = "makati",
      state = "metro manila",
      postcode = "1234"
    )
  )

  val user2 = User(
    title = "mr",
    username = "johndoe",
    email = "johndoe@.test.com",
    firstName = "John",
    lastName = " Doe",
    phoneNo = "123-4567",
    gender = "Male",
    birthdate = 32132109,
    location = Location(
      street = "dito",
      city = "makati",
      state = "metro manila",
      postcode = "1234"
    )
  )

  val listOfUsers = listOf(
    user1,
    user2
  )
}