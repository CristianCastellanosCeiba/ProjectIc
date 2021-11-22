package com.example.projectic.viewModel

import androidx.lifecycle.*
import com.example.domain.entity.Motorcycle
import com.example.infrastructure.repository.MotorcycleRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MotorcycleViewModel(private val motorcycleRepository: MotorcycleRepositoryImpl): ViewModel() {

    private val _errors = MutableLiveData<Exception>()
    val errorsMotorcycle: LiveData<Exception> = _errors

    fun getRegistryMotorcycle(motorcycle: Motorcycle) {
        viewModelScope.launch {
            motorcycleRepository.registryMotorcycle(motorcycle)
        }
    }

    fun getPrice(registration: String) = liveData(Dispatchers.IO) {
        try {
            emit(motorcycleRepository.payment(registration))
        } catch (e: Exception) {
            emit(e)
        }

    }

    fun getDeleteMotorcycle(registration: String) {
        viewModelScope.launch {
            try {
                motorcycleRepository.exitMotorcycle(registration)
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

    fun getAllMotorcycle() = liveData(Dispatchers.IO) {
        emit(motorcycleRepository.getMotorcycles())
    }
}

class MotorcycleViewModelFactory(private val motorcycleRepository: MotorcycleRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MotorcycleRepositoryImpl::class.java).newInstance(motorcycleRepository)
    }

}