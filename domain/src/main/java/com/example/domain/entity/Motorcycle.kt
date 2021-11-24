package com.example.domain.entity

import java.util.*

class Motorcycle(var cylinder: Int, override var registration: String,
                      override var type: String, override var hourEntry: Date
): Vehicle(registration, hourEntry, type) {

    fun calculatePay(): Long {
        var totalValue = calculatePayment(4000, 500)
        if (cylinder >= 500) {
            totalValue += 2000
        }
        return totalValue
    }

    data class MotorcycleList(val result: List<Motorcycle> = listOf())

}
