package com.yagosouza.meucarro.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yagosouza.meucarro.domain.usecase.GetCarDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getCarDetailUseCase: GetCarDetailUseCase,
    private val id: Long,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    private val _action = MutableSharedFlow<DetailAction>()

    val state = _state.asStateFlow()
    val action = _action.asSharedFlow()

    fun getDetail() {
        viewModelScope.launch(dispatcher) {
            getCarDetailUseCase(id)
                .onStart { _state.update { it.onLoading() } }
                .onCompletion { _state.update { it.onFinishedLoading() } }
                .catch { Log.e("TESTE", "Deu ruim", it) }
                .collect { car -> _state.update { it.onCarsReceived(car) } }
        }

    }

    fun onButtonClick() {
        sendAction(DetailAction.NavigateToHome)
    }

    private fun sendAction(action: DetailAction) = viewModelScope.launch(dispatcher) {
        _action.emit(action)
    }
}