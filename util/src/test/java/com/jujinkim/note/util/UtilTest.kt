package com.jujinkim.note.util

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class UtilTest {

    @Test
    fun millisToDateTimeString() {
        // format : d MMM yyyy, HH:mm:ss
        assertEquals("6 Apr 2022, 00:08:46", Util.millisToDateTimeString(1649171326335))
        assertEquals("20 Oct 1992, 14:20:35", Util.millisToDateTimeString(719558435000))
        assertEquals("2 Mar 2011, 09:00:00", Util.millisToDateTimeString(1299024000000))
        assertEquals("28 Dec 2135, 17:25:30", Util.millisToDateTimeString(5238116730000))
    }

    @Test
    fun millisToDateString() {
        // format : d MMM yyyy
        assertEquals("6 Apr 2022", Util.millisToDateString(1649171326335))
        assertEquals("20 Oct 1992", Util.millisToDateString(719558435000))
        assertEquals("2 Mar 2011", Util.millisToDateString(1299024000000))
        assertEquals("28 Dec 2135", Util.millisToDateString(5238116730000))
    }

    @Test
    fun calcExpiredTimeByDays() {
        assertEquals(1651244400000, Util.calcExpiredTimeByDays(1650623597516, 7))
    }
}