package com.example.domain.entity

import java.text.SimpleDateFormat
import java.util.*

class Auto(
    override var registration: String, override var hourEntry: Date, type: String,
): Vehicle(registration, hourEntry, type) {

    fun validateLetter(): Boolean {
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)
        var result = false
        if (registration.substring(0,1).lowercase(Locale.forLanguageTag("es")) != "a") {
            result = true
        } else if (dayOfTheWeek == "Monday" || dayOfTheWeek == "Sunday") {
            result = true
        }
        return result
    }

    fun calculatePay(): Long {
        return calculatePayment(8000, 1000)
    }

    data class AutoList(val result: List<Auto> = listOf())

}