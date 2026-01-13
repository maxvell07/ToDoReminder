package malok.todoreminder.features.listTasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import malok.todoreminder.features.listTasks.domain.ListRepository
import malok.todoreminder.features.listTasks.presentation.model.ListEffect
import malok.todoreminder.features.listTasks.presentation.model.ListUiState

class ListViewModel(
    private val repository: ListRepository
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
        observeTasks()
    }

    fun onItemClick(id: String) {
        viewModelScope.launch {
            _effect.emit(ListEffect.OpenDetails(id))
        }
    }

    fun onFloatButtonClick() {
        viewModelScope.launch {
            _effect.emit(ListEffect.OpenCreateTask())
        }
    }

    fun onTaskChecked(id: String, checked: Boolean) {
        val updatedTasks = _state.value.tasks.map { task ->
            if (task.id == id.toLong()) {
                task.copy(isDone = checked)
            } else {
                task
            }
        }
        _state.value = _state.value.copy(
            tasks = updatedTasks
        )
    }
    private fun observeTasks() {
        viewModelScope.launch {
            _state.value = ListUiState(tasks =emptyList(), isLoading = true,error = null)
            try {
                repository.observeTasks().collect { tasks ->
                    _state.value = ListUiState(tasks =tasks, isLoading = false,error = null)
                }
            } catch (e: Exception){
                _state.value = ListUiState(tasks =emptyList(), isLoading = false,error = e.message)
            }
        }
    }
}