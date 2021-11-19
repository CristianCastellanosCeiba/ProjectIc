package com.example.domain.entity

import java.util.*
import java.util.concurrent.TimeUnit

class Auto(override var registration: String, override var hourEntry: Date, override var hourExit: Date,
           type: String,): Vehicle(registration, hourEntry, hourExit, type) {

    private fun validateLetter(auto: Auto) {
        val day = Calendar.DAY_OF_WEEK
        if (auto.registration.startsWith("A")) {
            if (day == Calendar.MONDAY || day == Calendar.SUNDAY) {
                calculateHoursToPay(auto)
            } else {
                //Error
            }
        } else {
            calculateHoursToPay(auto)
        }
    }

    private fun calculateHoursToPay(auto: Auto) {
        val difference: Long = auto.hourEntry.time - auto.hourExit.time
        val time = TimeUnit.MILLISECONDS.toMinutes(difference).toDouble()
        println(time)
        calculatePayment(time)
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