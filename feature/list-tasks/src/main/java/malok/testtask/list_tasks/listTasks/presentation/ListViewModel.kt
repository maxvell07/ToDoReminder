package malok.testtask.list_tasks.listTasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import malok.testtask.list_tasks.listTasks.domain.ListRepository
import malok.testtask.list_tasks.listTasks.presentation.model.ListEffect
import malok.testtask.list_tasks.listTasks.presentation.model.ListIntent
import malok.testtask.list_tasks.listTasks.presentation.model.ListUiState

internal class ListViewModel(
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
            isLoading = true,
            error = null
        )
    )
    val state = _state.asStateFlow()

    init {
        onIntent(ListIntent.LoadTasks)
    }
    fun onIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.LoadTasks -> loadTasks()
            is ListIntent.ItemClicked -> openDetails(intent.id)
            is ListIntent.AddButtonClicked -> openCreateScreen()
            is ListIntent.TaskChecked -> updateTask(intent.id.toLong(), intent.checked)
            is ListIntent.onEdit -> openEditScreen(intent.id)
            is ListIntent.onDelete -> deleteTask(intent.id.toLong())
        }
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                repository.observeTasks().collect { tasks ->
                    _state.update { it.copy(tasks = tasks, isLoading = false) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(tasks = emptyList(), isLoading = false, error = e.message) }
                _effect.emit(ListEffect.ShowError(e.message ?: "Unknown error"))
            }
        }
    }

    private fun openDetails(id: String) {
        viewModelScope.launch {
            _effect.emit(ListEffect.OpenDetails(id))
        }
    }

    private fun openCreateScreen() {
        viewModelScope.launch {
            _effect.emit(ListEffect.OpenCreateTask())
        }
    }

    private fun updateTask(id: Long, checked: Boolean) {
        viewModelScope.launch {
            repository.updateStatus(id, checked)
        }
    }

    private fun deleteTask(id: Long) {
        viewModelScope.launch {
            repository.deleteTask(id)
        }
    }
    private fun openEditScreen(id: String) = viewModelScope.launch {
        _effect.emit(ListEffect.OpenEditTask(id))
    }
}