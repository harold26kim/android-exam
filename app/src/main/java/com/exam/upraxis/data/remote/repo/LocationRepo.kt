package com.exam.upraxis.data.remote.repo

import com.exam.upraxis.domain.model.Location

data class LocationRepo(
    val street: String?,
    val city: String?,
    val state: String?,
    val postcode: String?
) {
    constructor(item: Location?) : this(
        street = item?.street,
        city = item?.city,
        state = item?.state,
        postcode = item?.postcode
    )
    val toLocation: Location
        get() = Location(
            street = street,
            city = city,
            state = state,
            postcode = postcode
        )
}