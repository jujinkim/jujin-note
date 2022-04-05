package com.jujinkim.note.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UtilTest {

    @org.junit.Test
    fun msTimeToString() {
        // format : d MMM yyyy, HH:mm:ss
        assertEquals("6 Apr 2022, 00:08:46", Util.msTimeToString(1649171326335))
        assertEquals("20 Oct 1992, 14:20:35", Util.msTimeToString(719558435000))
        assertEquals("2 Mar 2011, 09:00:00", Util.msTimeToString(1299024000000))
        assertEquals("28 Dec 2135, 17:25:30", Util.msTimeToString(5238116730000))
    }
}