package com.example.projectic.viewModel

import androidx.lifecycle.*
import com.example.domain.entity.Motorcycle
import com.example.domain.repository.MotorcycleRepository
import com.example.domain.service.MotorcycleServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class MotorcycleViewModel @Inject constructor(private val motorcycleRepository: MotorcycleRepository): ViewModel() {
class MotorcycleViewModel (private val motorcycleServices: MotorcycleServices): ViewModel() {

    private val _errors = MutableLiveData<Exception>()
    val errorsMotorcycle: LiveData<Exception> = _errors

    fun getRegistryMotorcycle(motorcycle: Motorcycle) {
        viewModelScope.launch {
            motorcycleServices.registryMotorcycle(motorcycle)
        }
    }

    fun getPrice(registration: String) = liveData(Dispatchers.IO) {
        try {
            emit(motorcycleServices.payment(registration))
        } catch (e: Exception) {
            emit(e)
        }

    }

    fun getDeleteMotorcycle(registration: String) {
        viewModelScope.launch {
            try {
                motorcycleServices.exitMotorcycle(registration)
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

    fun getAllMotorcycle() = liveData(Dispatchers.IO) {
        emit(motorcycleServices.getMotorcycles())
    }

    fun getListMotorcycles() = liveData(Dispatchers.IO) {
        emit(motorcycleServices.getListMotorcycles())
    }
}

/*
class MotorcycleViewModelFactory(private val motorcycleRepository: MotorcycleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MotorcycleRepository::class.java).newInstance(motorcycleRepository)
    }

}*/
