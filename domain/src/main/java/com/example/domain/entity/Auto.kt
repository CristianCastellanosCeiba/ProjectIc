package com.example.domain.entity

import com.example.domain.exception.NoEntryDay
import java.text.SimpleDateFormat
import java.util.*

class Auto(
    override var registration: String, override var hourEntry: Date, type: String,
): Vehicle(registration, hourEntry, type) {

    fun validateLetter(): Boolean {
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)
        if (registration.substring(0,1).lowercase(Locale.forLanguageTag("es")) != "a") {
            return true
        } else if (dayOfTheWeek == "Monday" || dayOfTheWeek == "Sunday") {
            return true
        }
        throw NoEntryDay("Ingreso permitido los Lunes y Domingos")
    }

    fun calculatePay(): Long {
        return calculatePayment(8000, 1000)
    }

}