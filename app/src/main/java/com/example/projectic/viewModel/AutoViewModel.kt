package com.example.projectic.viewModel

import androidx.lifecycle.*
import com.example.domain.entity.Auto
import com.example.infrastructure.repository.AutoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AutoViewModel(private val autoRepository: AutoRepositoryImpl): ViewModel() {

    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    fun getRegistryAuto(auto: Auto, firstLetter: String) {
        viewModelScope.launch {
            try {
                autoRepository.registryAuto(auto, firstLetter)
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

    fun getPrice(registration: String) = liveData(Dispatchers.IO) {
        try {
            emit(autoRepository.payment(registration))
        } catch (e: Exception) {
            emit(e)
        }
    }

    fun getDeleteAuto(registration: String) {
        viewModelScope.launch {
            try {
                autoRepository.exitAuto(registration)
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

    fun getAllAutos() = liveData(Dispatchers.IO) {
        emit(autoRepository.getAutos())
    }
}

class AutoViewModelFactory(private val autoRepository: AutoRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AutoRepositoryImpl::class.java).newInstance(autoRepository)
    }

}