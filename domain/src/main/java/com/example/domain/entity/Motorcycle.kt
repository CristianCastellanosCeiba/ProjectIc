package com.example.domain.entity

import java.util.*

data class Motorcycle(var cylinder: Int, override var registration: String,
                      override var type: String, override var hourEntry: Date
): Vehicle(registration, hourEntry, type) {

    private val initRangeHoursDay = 9
    private val endRangeHoursDay = 24
    private val notDaysInParking = 0
    private val millisecondsInOneSecond = 1000
    private val secondsInOneMinute = 60
    private val minutesInOneHour = 60

    fun calculatePayment(): Long {
        var parkingHours = getParkingHours()
        var hoursToPay : Long = 0
        var daysToPay : Long = 0
        var totalValue : Long = 0

        if (parkingHours / endRangeHoursDay > notDaysInParking){
            daysToPay = parkingHours / endRangeHoursDay
            parkingHours %= endRangeHoursDay
        }

        if (parkingHours >= initRangeHoursDay){
            daysToPay++
        }else{
            hoursToPay = parkingHours
        }

        totalValue =  (daysToPay * 4000) + (hoursToPay * 500)

        if (cylinder >= 500) {
            totalValue += 2000
        }

        return totalValue
    }

    private fun getParkingHours(): Long {
        var diff = Date().time - hourEntry!!.time
        var minutesDiff = (diff / (secondsInOneMinute * millisecondsInOneSecond))
        return if (minutesDiff <= minutesInOneHour) {
            1
        } else {
            (diff / (minutesInOneHour * secondsInOneMinute * millisecondsInOneSecond))
        }
    }

}
