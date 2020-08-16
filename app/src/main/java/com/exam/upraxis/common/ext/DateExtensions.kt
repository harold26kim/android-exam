package com.exam.upraxis.common.ext

import java.text.SimpleDateFormat
import java.util.*


// DATE
fun Date.formatToHumanReadable(format: String): String {
    return SimpleDateFormat(format, Locale.getDefault())
        .format(this.time)
}
