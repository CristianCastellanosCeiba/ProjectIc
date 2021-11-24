package com.example.projectic.viewModel

import androidx.lifecycle.*
import com.example.domain.entity.Auto
import com.example.domain.repository.AutoRepository
import com.example.domain.service.AutoServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class AutoViewModel @Inject constructor(private val autoRepository: AutoRepository): ViewModel() {
class AutoViewModel (private val autoServices: AutoServices): ViewModel() {

    private val _errors = MutableLiveData<Exception>()
    val errors: LiveData<Exception> = _errors

    fun getRegistryAuto(auto: Auto) {
        viewModelScope.launch {
            try {
                autoServices.registryAuto(auto)
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

    fun getPrice(registration: String) = liveData(Dispatchers.IO) {
        try {
            emit(autoServices.payment(registration))
        } catch (e: Exception) {
            emit(e)
        }
    }

    fun getDeleteAuto(registration: String) {
        viewModelScope.launch {
            try {
                autoServices.exitAuto(registration)
            } catch (e: Exception) {
                _errors.value = e
            }
        }
    }

    fun getAllAutos() = liveData(Dispatchers.IO) {
        emit(autoServices.getAutos())
    }
}

/*
class AutoViewModelFactory(private val autoRepository: AutoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AutoRepository::class.java).newInstance(autoRepository)
    }

}*/
