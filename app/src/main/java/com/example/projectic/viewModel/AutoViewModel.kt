package com.example.projectic.viewModel

import androidx.lifecycle.*
import com.example.domain.entity.Auto
import com.example.domain.entity.Motorcycle
import com.example.domain.repository.AutoRepository
import com.example.infrastructure.repository.AutoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AutoViewModel(private val autoRepository: AutoRepositoryImpl): ViewModel() {

    private val _categories = MutableLiveData<Auto>()
    val categories: LiveData<Auto> = _categories

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

    fun getPrice(auto: Auto, hourExit: Date) {
        println("paso por el viewmodel")
        viewModelScope.launch {
            autoRepository.payment(auto, hourExit)
        }
    }

    fun getDeleteAuto(registration: String) = liveData(Dispatchers.IO) {
        try {
            emit(autoRepository.exitAuto(registration))
        } catch (e: Exception) {
            emit(e)
        }
    }
}

class AutoViewModelFactory(private val autoRepository: AutoRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AutoRepositoryImpl::class.java).newInstance(autoRepository)
    }

}