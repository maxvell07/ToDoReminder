package malok.todoreminder.features.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import malok.todoreminder.features.detail.domain.DetailRepository
import malok.todoreminder.features.detail.presentation.model.DetailUiState

class DetailViewModel(
    private val repository: DetailRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState(isLoading = true))
    val state: StateFlow<DetailUiState> = _state

    fun observeTask(id: Long) {
        viewModelScope.launch {
            repository.observeTaskById(id)
                .collect { task ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        task = task,
                        error = null
                    )
                }
        }
    }

    fun onTaskChecked(id: Long?, checked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStatus(id!!, checked)
        }
    }
}
