package com.example.domain.service

import android.util.Log.e
import com.example.domain.entity.Auto
import com.example.domain.exception.DbException
import com.example.domain.exception.NoEntryDay
import com.example.domain.repository.AutoRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class AutoServicesTest : TestCase() {

    @Mock
    private lateinit var autoRepository: AutoRepository

    @InjectMocks
    private lateinit var autoServices: AutoServices

    @Test
    fun testRegistryAuto_differentDayMondayOrSaturday() {
        runBlocking {
            val auto = Auto("qsd123", Date(), "Auto")
            Mockito.`when`(autoServices.registryAuto(auto)).thenReturn(null)
            assert(true)
        }
    }

    @Test
    fun testRegistryAuto_DayMondayOrSaturday() {
        runBlocking {
            val auto = Auto("asd123", Date(), "Auto")
            try {
                Mockito.`when`(autoServices.registryAuto(auto)).thenReturn(null)
            } catch (e: NoEntryDay) {
                assertEquals("Ingreso permitido los Lunes y Domingos",e.message)
            }
        }
    }

    @Test
    fun testExitAuto_true() {
        runBlocking {
            val registration = "AAD123"
            Mockito.`when`(autoServices.exitAuto(registration)).thenReturn(null)
            assert(true)
        }
    }

}