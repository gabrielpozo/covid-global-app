package com.example.covidglobal.general

import java.text.SimpleDateFormat
import java.util.*


/**
 * Pattern: dd/MM/yyyy HH:mm:ss
 */
fun String.formatToViewDateTimeDefaults(): String {
    val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return formatter.format(parser.parse(this)?: "2019-09-01T20:34:23Z").toString()
}

