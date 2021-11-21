package com.example.domain.entity

import android.annotation.SuppressLint
import com.example.domain.exception.NoEntryDay
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Auto(
    override var registration: String, override var hourEntry: Date, override var hourExit: Date?,
    type: String,
): Vehicle(registration, hourEntry, hourExit, type) {

    @SuppressLint("SimpleDateFormat")
    fun validateLetter(firstLetter: String): Boolean {
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)
        if (firstLetter == "A" && (dayOfTheWeek == "Monday" || dayOfTheWeek == "Sunday")) {
            return true
        }
        throw NoEntryDay("Ingreso permitido los Lunes y Domingos")
    }

    fun calculateHoursToPay(auto: Auto, hourExit: Date): Double {
        val difference: Long = auto.hourEntry.time - hourExit.time
        val time = TimeUnit.MILLISECONDS.toMinutes(difference).toDouble()
        println("este es el tiempo $time")
        return calculatePayment(time)
    }

    private fun calculatePayment(time: Double): Double {
        var totalPayment = 0.0
        if (time in 9.0..24.0) {
            totalPayment = 8000.0
        } else if (time < 8.9) {
            totalPayment = time * 1000.0
        }
        return totalPayment
    }
}