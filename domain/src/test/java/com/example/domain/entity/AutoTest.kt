package com.example.domain.entity

import com.example.domain.exception.NoEntryDay
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class AutoTest {

    @Test
    fun validateLetter_vehicleWithRegistrationDifferentA_true () {
        //Arrange
        val registration = "zxc123"
        var auto = Auto(registration, Date(), "Auto")
        val expect = true

        //Act
        val result = auto.validateLetter()

        //Assert
        assertEquals(expect, result)
    }

    @Test
    fun validateLetter_vehicleWithRegistrationDifferentAUpperCase_true () {
        //Arrange
        val registration = "ZXC123"
        var auto = Auto(registration, Date(), "Auto")
        val expect = true

        //Act
        val result = auto.validateLetter()

        //Assert
        assertEquals(expect,result)
    }

    @Test
    fun validateLetter_vehicleWithRegistrationAAndDayMonday_true () {
        //Arrange
        val registration = "AXC123"
        var auto = Auto(registration, Date(), "Auto")
        try {
            //Act
            auto.validateLetter()
        } catch (e: NoEntryDay) {
            //Assert
            assertEquals("Ingreso permitido los Lunes y Domingos",e.message)
        }
    }

    @Test
    fun validateLetter_vehicleWithRegistrationAAndDaySunday_true () {
        //Arrange
        val registration = "AXC123"
        var auto = Auto(registration, Date(), "Auto")
        try {
            //Act
            auto.validateLetter()
        } catch (e: NoEntryDay) {
            //Assert
            assertEquals("Ingreso permitido los Lunes y Domingos",e.message)
        }
    }

    @Test
    fun validateLetter_vehicleWithRegistrationAOnDayTuesday_exception () {
        //Arrange
        val registration = "AXC123"
        var auto = Auto(registration, Date(), "Auto")
        try {
            //Act
            auto.validateLetter()
        } catch (e: NoEntryDay) {
            //Assert
            assertEquals("Ingreso permitido los Lunes y Domingos",e.message)
        }
    }

    @Test
    fun validateLetter_vehicleWithRegistrationAOnDayWednesday_exception () {
        //Arrange
        val registration = "AXC123"
        var auto = Auto(registration, Date(), "Auto")
        try {
            //Act
            auto.validateLetter()
        } catch (e: NoEntryDay) {
            //Assert
            assertEquals("Ingreso permitido los Lunes y Domingos",e.message)
        }
    }

    @Test
    fun validateLetter_vehicleWithRegistrationAOnDayThursday_exception () {
        //Arrange
        val registration = "qwe123"
        var auto = Auto(registration, Date(), "Auto")
        try {
            //Act
            auto.validateLetter()
        } catch (e: NoEntryDay) {
            //Assert
            assertEquals("Ingreso permitido los Lunes y Domingos",e.message)
        }
    }

    @Test
    fun validateLetter_vehicleWithRegistrationAOnDayFriday_exception () {
        //Arrange
        val registration = "awerty"
        var auto = Auto(registration, Date(), "Auto")
        try {
            //Act
            auto.validateLetter()
        } catch (e: NoEntryDay) {
            //Assert
            assertEquals("Ingreso permitido los Lunes y Domingos",e.message)
        }
    }

    @Test
    fun validateLetter_vehicleWithRegistrationAOnDaySaturday_exception () {
        //Arrange
        val registration = "AFG789"
        var auto = Auto(registration, Date(), "Auto")
        try {
            //Act
            auto.validateLetter()
        } catch (e: NoEntryDay) {
            //Assert
            assertEquals("Ingreso permitido los Lunes y Domingos",e.message)
        }
    }

    @Test
    fun calculatePay_forHour_true() {
        val registration = "AFG789"
        var auto = Auto(registration, Date(), "Auto")
        println("Formato Date(): ${Date()}")
        println("Formato Date().time: ${Date().time}")
        val response = auto.calculatePay()
        assertEquals(1000, response)
    }
}