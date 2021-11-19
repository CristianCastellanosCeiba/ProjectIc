package com.example.domain.entity

import java.util.*
import java.util.concurrent.TimeUnit

data class Motorcycle(var cylinder: Int, override var registration: String,
                      override var type: String, override var hourEntry: Date, override var hourExit: Date
): Vehicle(registration, hourEntry, hourExit, type) {

    private var totalPayment: Double = 0.0

    private fun calculateHoursToPay(motorcycle: Motorcycle) {
        val difference: Long = motorcycle.hourEntry.time - motorcycle.hourExit.time
        val time = TimeUnit.MILLISECONDS.toMinutes(difference).toDouble()
        println(time)
        calculatePayment(time, motorcycle)
    }

    private fun calculatePayment(time: Double, motorcycle: Motorcycle): Double {
        when (time) {
            in 9.0..24.0 -> {
                totalPayment = 4000.0
            }
            in 0.0..8.59 -> {
                totalPayment = time * 5 + 2000.0
            }
        }
        if (motorcycle.cylinder > 500) {
            totalPayment += 2000
        }
        return totalPayment
    }

}
