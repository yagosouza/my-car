package com.yagosouza.meucarro.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yagosouza.meucarro.domain.model.Car
import com.yagosouza.meucarro.domain.usecase.GetCarsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCarsUseCase: GetCarsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    private val _action = MutableSharedFlow<HomeAction>()

    val state = _state.asStateFlow()
    val action = _action.asSharedFlow()

    fun getCars() {
        viewModelScope.launch(dispatcher) {
            getCarsUseCase().onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch { throwable ->
                Log.e("TESTE", "Deu ruim", throwable)
            }.collect { cars ->
                _state.update { it.onCarsReceived(cars) }
            }
        }
    }

    fun onCarItemClicked(car: Car) {
        val action = HomeAction.ShowCarDetails(car.id)
        sendAction(action)
    }

    fun onButtonClick() {
        sendAction(HomeAction.FinishApp)
    }

    private fun sendAction(action: HomeAction) = viewModelScope.launch(dispatcher) {
        _action.emit(action)
    }
}