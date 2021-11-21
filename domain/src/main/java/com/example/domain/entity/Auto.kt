package com.example.domain.entity

import android.annotation.SuppressLint
import com.example.domain.exception.NoEntryDay
import java.text.SimpleDateFormat
import java.util.*

class Auto(
    override var registration: String, override var hourEntry: Date, type: String,
): Vehicle(registration, hourEntry, type) {

    private val initRangeHoursDay = 9
    private val endRangeHoursDay = 24
    private val notDaysInParking = 0
    private val millisecondsInOneSecond = 1000
    private val secondsInOneMinute = 60
    private val minutesInOneHour = 60

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

        totalValue =  (daysToPay * 8000) + (hoursToPay * 1000)

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