package com.example.projectic.viewModel

import androidx.lifecycle.*
import com.example.domain.entity.Auto
import com.example.domain.repository.AutoRepository
import com.example.infrastructure.repository.AutoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AutoViewModel(private val autoRepository: AutoRepositoryImpl): ViewModel() {

    fun getPost(auto: Auto) = liveData(Dispatchers.IO) {
        try {
            emit(autoRepository.registryAuto(auto))
        } catch (e: Exception) {
            emit(e)
        }
    }
}