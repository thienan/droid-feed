package com.droidfeed.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateTimeUtils @Inject constructor() {

    enum class DateFormat(val format: SimpleDateFormat) {
        RSS(SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)),
        ATOM(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ", Locale.ENGLISH))
    }

    /**
     * Returns the timestamp value of the given date.
     *
     * @param date
     * @param simpleDateFormat
     *
     * @return the number of milliseconds since January 1, 1970, 00:00:00 GMT or null
     */
    @Synchronized
    fun getTimeStampFromDate(
        date: String,
        simpleDateFormat: SimpleDateFormat
    ): Long? {
        return try {
            val mDate = simpleDateFormat.parse(date)
            mDate?.time
        } catch (e: ParseException) {
            logStackTrace(e)
            0L
        }
    }
}