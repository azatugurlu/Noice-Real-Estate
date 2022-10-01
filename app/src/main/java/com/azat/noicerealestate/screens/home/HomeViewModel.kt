package com.azat.noicerealestate.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azat.domain.Output.Status
import com.azat.domain.entity.Property
import com.azat.domain.usecases.PropertiesUseCases
import com.azat.noicerealestate.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val propertiesUseCases: PropertiesUseCases,
): BaseViewModel() {

    private var _homeViewState = mutableStateOf(HomeViewState())
    val homeViewState: State<HomeViewState> = _homeViewState

    init {
        fetchProperties()
    }

    private fun fetchProperties() {
        viewModelScope.launch {
            propertiesUseCases.execute().collect {
                val homeViewState = HomeViewState()
                when(it.status) {
                    Status.SUCCESS -> {
                        homeViewState.isLoading = false
                        homeViewState.properties = it.data!!
                    }
                    Status.ERROR -> {
                        homeViewState.isLoading = false
                        homeViewState.errorMessage = it.message.toString()
                    }
                    Status.LOADING -> {
                        homeViewState.isLoading = true
                    }
                }
                _homeViewState.value = homeViewState
            }
        }
    }

    data class HomeViewState(
        var properties: List<Property> = emptyList(),
        var errorMessage: String = "",
        var isLoading: Boolean = false
    )
}

