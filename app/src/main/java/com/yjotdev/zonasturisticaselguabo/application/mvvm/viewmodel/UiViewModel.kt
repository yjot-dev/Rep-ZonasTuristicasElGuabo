package com.yjotdev.zonasturisticaselguabo.application.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.yjotdev.zonasturisticaselguabo.application.mvvm.model.UiModel

@HiltViewModel
class UiViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiModel())
    val uiState: StateFlow<UiModel> = _uiState.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        cleanState()
    }
    /**
     * Limpia el estado del ViewModel
     **/
    fun cleanState() {
        _uiState.value = UiModel()
    }
    /**
     * Cambia el estado del titulo del marcador
     **/
    fun setTitle(value: String){
        _uiState.update { state -> state.copy(title = value) }
    }
    /**
     * Cambia el estado de la imagen del marcador
     **/
    fun setImageUrl(value: String){
        _uiState.update { state -> state.copy(imageUrl = value) }
    }
    /**
     * Cambia el estado de la descripcion del marcador
     **/
    fun setDescription(value: String){
        _uiState.update { state -> state.copy(description = value) }
    }
}