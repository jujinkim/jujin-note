package com.jujinkim.note.util

import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun millisToDateTimeString(ms: Long) : String {
        val sdf = SimpleDateFormat("d MMM yyyy, HH:mm:ss", Locale.getDefault())
        val date = Date(ms)
        return sdf.format(date)
    }

    fun millisToDateString(ms: Long) : String {
        val sdf = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        val date = Date(ms)
        return sdf.format(date)
    }

    /**
     * Return expired milliseconds time by given day.
     * current millis + given day in millis
     */
    fun calcExpiredTimeByDays(currentMillis: Long, days: Int) : Long {
        val millisDays = 1000 * 60 * 60 * 24 * (days + 1)   // expired at the end of day

        val cal = Calendar.getInstance().apply {
            timeInMillis = currentMillis
        }
        val dateCal = Calendar.getInstance().apply {
            set(cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DATE], 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return dateCal.timeInMillis + millisDays
    }
}