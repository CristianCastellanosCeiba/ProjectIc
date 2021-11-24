package com.example.domain.entity

import java.util.*

abstract class Vehicle(
    open var registration: String,
    open var hourEntry: Date,
    open var type: String
) {

    private fun getParkingHours(): Long {
        var diff = Date().time - hourEntry.time
        var minutesDiff = (diff / (60 * 1000))
        return if (minutesDiff <= 60) {
            1
        } else {
            (diff / (60 * 60 * 1000))
        }
    }

    protected fun calculatePayment(payPorDay: Int, payPorHour: Int): Long {
        var parkingHours = getParkingHours()
        var hoursToPay: Long = 0
        var daysToPay: Long = 0

        if (parkingHours / 24 > 0) {
            daysToPay = parkingHours / 24
            parkingHours %= 24
        }

        if (parkingHours >= 9) {
            daysToPay++
        } else {
            hoursToPay = parkingHours
        }

        return (daysToPay * payPorDay) + (hoursToPay * payPorHour)
    }

}