package com.example.domain.entity

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MotorcycleTest {

    @Test
    fun calculatePay_ccMinor500_500ForHour() {
        val registration = "ASD123"
        var motorcycle = Motorcycle(125, registration, "Motorcycle", Date())
        val response = motorcycle.calculatePay()
        assertEquals(500, response)
    }

    @Test
    fun calculatePay_ccMayor500_2500ForHour() {
        val registration = "ASD123"
        var motorcycle = Motorcycle(650, registration, "Motorcycle", Date())
        val response = motorcycle.calculatePay()
        assertEquals(2500, response)
    }
}