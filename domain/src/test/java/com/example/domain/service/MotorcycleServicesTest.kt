package com.example.domain.service

import android.view.translation.Translator
import com.example.domain.entity.Motorcycle
import com.example.domain.repository.MotorcycleRepository
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
class MotorcycleServicesTest : TestCase() {

    @Mock
    lateinit var motorcycleRepository: MotorcycleRepository

    @InjectMocks
    private lateinit var motorcycleServices: MotorcycleServices
    private val motorcycle = Motorcycle(1, "asd123", "Motorcycle", Date())
    private val motorcycle1 = Motorcycle(1, "asd123", "Motorcycle", Date())
    private val listMotorcycle = listOf(motorcycle, motorcycle1)

    @Test
    fun testGetMotorcycles_countMotorcyclesInDb1_true() {
        runBlocking {
            Mockito.`when`(motorcycleRepository.getMotorcycles()).thenReturn(1)
            val result = motorcycleServices.getMotorcycles()
            assertEquals(1, result)
        }
    }

    @Test
    fun testGetMotorcycles_countMotorcyclesInDb0_true() {
        runBlocking {
            Mockito.`when`(motorcycleRepository.getMotorcycles()).thenReturn(0)
            val result = motorcycleServices.getMotorcycles()
            assertEquals(0, result)
        }
    }

}