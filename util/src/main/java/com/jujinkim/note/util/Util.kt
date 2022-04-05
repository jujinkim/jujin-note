package com.jujinkim.note.util

import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun msTimeToString(ms: Long) : String {
        val sdf = SimpleDateFormat("d MMM yyyy, HH:mm:ss", Locale.getDefault())
        val date = Date(ms)
        return sdf.format(date)
    }
}