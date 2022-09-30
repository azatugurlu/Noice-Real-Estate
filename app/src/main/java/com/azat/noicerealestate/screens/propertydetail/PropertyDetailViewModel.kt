package com.azat.noicerealestate.screens.propertydetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.azat.domain.entity.Property
import com.azat.domain.usecases.PropertiesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    propertiesUseCases: PropertiesUseCases,
): ViewModel() {

    private val propertyId: String = checkNotNull(savedStateHandle["propertyId"])

    private var _propertyDetailViewState = mutableStateOf(PropertyDetailViewState())
    val propertyDetailViewState: State<PropertyDetailViewState> = _propertyDetailViewState

    init {
        val propertyDetailViewState = PropertyDetailViewState()
        propertyDetailViewState.property = propertiesUseCases.getProperty(propertyId.toInt())
        propertyDetailViewState.isLoading = false
        _propertyDetailViewState.value = propertyDetailViewState
    }

    data class PropertyDetailViewState(
        var property: Property? = null,
        var errorMessage: String = "",
        var isLoading: Boolean = false
    )
}