package net.larntech.retrofit.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    public val formatterDateTimeV1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)

    fun formatDateTime(date: String?): String {
        return SimpleDateFormat("EEE,MMM dd yyyy", Locale.ENGLISH).format(
            formatterDateTimeV1.parseObject(
                date
            )
        )
    }

}