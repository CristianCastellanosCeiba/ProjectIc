package com.example.projectic.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Motorcycle
import com.example.domain.repository.MotorcycleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MotorcycleViewModel @Inject constructor(private val motorcycleRepository: MotorcycleRepository): ViewModel() {

    private val _errors = MutableLiveData<Exception>()
    val errorsMotorcycle: LiveData<Exception> = _errors

    fun getRegistryMotorcycle(motorcycle: Motorcycle) {
        viewModelScope.launch {
            motorcycleRepository.registryMotorcycle(motorcycle)
        }
    }

    /*fun getPrice(registration: String) = liveData(Dispatchers.IO) {
        try {
            emit(motorcycleRepository.payment(registration))
        } catch (e: Exception) {
            emit(e)
        }

    }*/

    fun getDeleteMotorcycle(registration: String) {
        viewModelScope.launch {
            try {
                motorcycleRepository.exitMotorcycle(registration)
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

    /*fun getAllMotorcycle() = liveData(Dispatchers.IO) {
        emit(motorcycleRepository.getMotorcycles())
    }*/
}

/*
class MotorcycleViewModelFactory(private val motorcycleRepository: MotorcycleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MotorcycleRepository::class.java).newInstance(motorcycleRepository)
    }

}*/
