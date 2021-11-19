package com.example.domain.entity

import java.util.*
import java.util.concurrent.TimeUnit

fun validateLetter(vehicle: Vehicle) {
    val day = Calendar.DAY_OF_WEEK
    if (vehicle.registration.startsWith("A")) {
        if (day == Calendar.MONDAY || day == Calendar.SUNDAY) {
            calculateHoursToPay(vehicle)
        } else {
            //Error
        }
    } else {
        calculateHoursToPay(vehicle)
    }
}

fun calculateHoursToPay(vehicle: Vehicle) {
    val difference: Long = vehicle.hourEntry.time - vehicle.hourExit.time
    val time = TimeUnit.MILLISECONDS.toMinutes(difference)
    println(time)
    calculatePayment(time, vehicle)
}

private fun calculatePayment(time: Long, vehicle: Vehicle): Double {
    var totalPayment = 0.0
    when(vehicle) {
        is Motorcycle -> ({
            when {
                time in 9..24 -> totalPayment = 4000.0
                time < 24 -> totalPayment = time * 500.0
                vehicle.cc -> totalPayment += 2000.0
            }
        }) as Double
        else -> {
            when {
                time in 9..24 -> totalPayment = 8000.0
                time < 24 -> totalPayment = time * 1000.0
            }
        }
    }
    return totalPayment
}
