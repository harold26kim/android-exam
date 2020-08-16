package com.exam.upraxis.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.lang.StringBuilder

@Parcelize
class Location(
    val street: String?,
    val city: String?,
    val state: String?,
    val postcode: String?
) : Parcelable {
    @IgnoredOnParcel
    val displayAddress = StringBuilder()
        .apply {
            street?.let {
                append("$it, ")
            }
            city?.let {
                append("$it, ")
            }
            state?.let {
                append("$it, ")
            }
            postcode?.let {
                append("$it")
            }
        }
}