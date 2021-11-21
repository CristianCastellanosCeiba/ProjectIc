package com.example.domain.entity

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.domain.exception.NoEntryDay
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime
import java.util.*
import java.util.concurrent.TimeUnit

class Auto(
    override var registration: String, override var hourEntry: Date, type: String,
): Vehicle(registration, hourEntry, type) {

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getParkingHours(): Double {
        var diff = Date().time - hourEntry!!.time
        var minutesDiff = (diff / (secondsInOneMinute * millisecondsInOneSecond))
        var hour = LocalTime.MIN.plus(Duration.ofMinutes(minutesDiff)).toString()
        println("horas $hour")
        return calculatePayment(hour)
    }

    private fun calculatePayment(time: String): Double {
        var totalPayment = 0.0
        when(time) {
            in "00:00".."08:59" -> {
                when(time) {
                    in "00:00".."00:59" -> {
                        totalPayment = 1000.0
                    }in "01:00".."01:59" -> {
                        totalPayment = 2000.0
                    }in "02:00".."02:59" -> {
                        totalPayment = 3000.0
                    }in "03:00".."03:59" -> {
                        totalPayment = 4000.0
                    }in "04:00".."04:59" -> {
                        totalPayment = 5000.0
                    }in "05:00".."05:59" -> {
                        totalPayment = 6000.0
                    }in "06:00".."06:59" -> {
                        totalPayment = 7000.0
                    }in "07:00".."07:59" -> {
                        totalPayment = 8000.0
                    }in "08:00".."08:59" -> {
                        totalPayment = 9000.0
                    }
                }
            }
            in "09:00".."23:59" -> {
                totalPayment = 8000.0
            }
        }
        return totalPayment
    }
}