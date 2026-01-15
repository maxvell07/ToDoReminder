package malok.todoreminder.features.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import malok.todoreminder.features.detail.domain.DetailRepository
import malok.todoreminder.features.detail.presentation.model.DetailEffect
import malok.todoreminder.features.detail.presentation.model.DetailIntent
import malok.todoreminder.features.detail.presentation.model.DetailUiState

class DetailViewModel(
    private val repository: DetailRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState(isLoading = true))
    val state: StateFlow<DetailUiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DetailEffect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadTask -> loadTask(intent.id)
            is DetailIntent.TaskChecked -> updateTask(intent.id, intent.checked)
        }
    }

    private fun loadTask(id: Long) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                repository.observeTaskById(id)
                    .collect { task ->
                        _state.value = _state.value.copy(
                            task = task,
                            isLoading = false,
                            error = null
                        )
                    }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
                _effect.emit(DetailEffect.ShowError(e.message ?: "Unknown error"))
            }
        }
    }

    private fun updateTask(id: Long, checked: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateStatus(id, checked)
            }
        }
    }
}
