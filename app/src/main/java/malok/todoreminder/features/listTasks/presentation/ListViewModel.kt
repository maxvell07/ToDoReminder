package malok.todoreminder.features.listTasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import malok.todoreminder.features.listTasks.domain.ListRepository
import malok.todoreminder.features.listTasks.presentation.model.Item
import malok.todoreminder.features.listTasks.presentation.model.ListEffect
import malok.todoreminder.features.listTasks.presentation.model.ListUiState


class ListViewModel(
    repository: ListRepository
) : ViewModel() {

    private val _effect = MutableSharedFlow<ListEffect>(
        replay = 0,
        extraBufferCapacity = 1
    )
    val effect = _effect.asSharedFlow()

    private val _state = MutableStateFlow<ListUiState>(
        ListUiState(
            tasks = emptyList(),
            isLoading = false,
            error = null
        )
    )
    val state = _state.asStateFlow()

    init {
        loadTasks()
    }

    fun onItemClick(id: String) {
        viewModelScope.launch {
            _effect.emit(ListEffect.OpenDetails(id))
        }
    }

    fun sendEffect() {
        viewModelScope.launch {
            _effect.emit(ListEffect.ShowError("Effect -_-"))
        }
    }

    fun loadTasks() {
        viewModelScope.launch {
            _state.value = ListUiState(tasks = emptyList(), isLoading = true, error = null)
            try {
                //delay(5000)
                val list = listOf(
                    Item("1", "task1"),
                    Item("2", "task2"),
                    Item("3", "task3")
                )
                _state.value = ListUiState(tasks = list, isLoading = false, error = null)
            } catch (e: Exception) {
                _state.value = ListUiState(tasks = emptyList(), isLoading = false, error = "ERROR")
                _effect.emit(ListEffect.ShowError("ERROR -_-${e.message}"))
            }
        }
    }

}