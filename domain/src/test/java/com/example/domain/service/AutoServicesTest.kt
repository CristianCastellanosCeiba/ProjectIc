package com.example.domain.service

import android.util.Log.e
import com.example.domain.entity.Auto
import com.example.domain.exception.DbException
import com.example.domain.exception.NoEntryDay
import com.example.domain.repository.AutoRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.internal.runners.statements.Fail
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
//class AutoServicesTest : TestCase() {
class AutoServicesTest {

    @Mock
    lateinit var autoRepository: AutoRepository

    @InjectMocks
    private lateinit var autoServices: AutoServices

    @Test
    fun testAuto_countAutosInDb1_true() {
        runBlocking {
            Mockito.`when`(autoRepository.getAutos()).thenReturn(1)
            val result = autoServices.getAutos()
            assertEquals(1,result)
        }
    }

    @Test
    fun testAuto_countAutosInDb0_true() {
        runBlocking {
            Mockito.`when`(autoRepository.getAutos()).thenReturn(0)
            val result = autoServices.getAutos()
            assertEquals(0, result)
        }
    }

    @Test
    fun testAuto_countAutosInDb_exception() {
        runBlocking {
            try {
                autoServices.getAutos()
            } catch (e: DbException) {
                assertEquals("No hay registros", e.message)
            }
        }
    }

}