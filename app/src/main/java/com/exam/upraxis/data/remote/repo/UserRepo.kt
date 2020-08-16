package com.exam.upraxis.data.remote.repo

import com.exam.upraxis.domain.model.User
import com.google.gson.annotations.SerializedName


data class UserRepo(
    val title: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastname: String?,
    val username: String?,
    val email: String?,
    @SerializedName("phone_number")
    val phoneNo: String?,
    val gender: String?,
    val birthdate: Long?,
    val location: LocationRepo?
) {

    constructor(item: User) : this(
        title = item.title,
        firstName = item.firstName,
        lastname = item.lastName,
        username = item.username,
        email = item.email,
        phoneNo = item.phoneNo,
        gender = item.gender,
        birthdate = item.birthdate,
        location = LocationRepo(item.location)
    )
    val toUser: User
        get() = User(
            title = title,
            firstName = firstName,
            lastName = lastname,
            username = username,
            email = email,
            phoneNo = phoneNo,
            gender = gender,
            birthdate = birthdate,
            location = location?.toLocation
        )
}