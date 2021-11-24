package com.example.domain.entity


import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test
import java.util.*

class VehicleTest {

    private val registration = "ASD123"
    var vehicle: Vehicle = Auto(registration, Date(), "Auto")

    @Test
    fun testGetParkingHours_1HourAuto_true() {
        val response = vehicle.getParkingHours()
        assertEquals(1, response)
    }

    @Test
    fun testCalculatePayment_1HourCar_true() {
        val response = vehicle.calculatePayment(8000, 1000)
        assertEquals(1000, response)
    }
}