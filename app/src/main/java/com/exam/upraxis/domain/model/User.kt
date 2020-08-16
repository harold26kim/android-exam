package com.exam.upraxis.domain.model

import android.os.Parcelable
import com.exam.upraxis.common.ext.formatToHumanReadable
import com.exam.upraxis.domain.constants.Constants
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class User(
    val title: String?,
    val firstName: String?,
    val lastName: String?,
    val username: String?,
    val email: String?,
    val phoneNo: String?,
    val gender: String?,
    val birthdate: Long?,
    val location: Location?
) : Parcelable {
    @IgnoredOnParcel
    val displayBirthday = Date(birthdate ?: 0).formatToHumanReadable(Constants.HUMAN_READABLE_DATE)
}