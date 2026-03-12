package malok.testtask.edit_task.editTask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import malok.testtask.core.domain.Task
import malok.testtask.edit_task.editTask.domain.EditRepository
import malok.testtask.edit_task.editTask.presentation.model.EditEffect
import malok.testtask.edit_task.editTask.presentation.model.EditIntent
import malok.testtask.edit_task.editTask.presentation.model.EditUiState

internal class EditViewModel(
    id: String,
    private val repository: EditRepository
) : ViewModel() {


    private val _state = MutableStateFlow(EditUiState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EditEffect>()
    val effect = _effect.asSharedFlow()

    init {
        loadTask(id)
    }

    fun onIntent(intent: EditIntent) {
        when (intent) {

            is EditIntent.TitleChanged ->
                updateTask { it.copy(title = intent.value) }

            is EditIntent.DescriptionChanged ->
                updateTask { it.copy(description = intent.value) }

            is EditIntent.TimeChanged ->
                updateTask { it.copy(date = intent.value) }

            EditIntent.SaveTask ->
                saveTask()

            is EditIntent.DeleteTask -> deleteTask(intent.id)
        }
    }

    private fun updateTask(
        reducer: (Task) -> Task
    ) {
        _state.update {
            it.copy(task = reducer(it.task))
        }
    }

    private fun loadTask(taskId: String) {
        viewModelScope.launch {
            try {
                val task = repository.getTaskById(taskId.toLong())
                _state.update {
                    it.copy(
                        task = task,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        error = e.message ?: "Load error",
                        isLoading = false
                    )
                }
            }
        }
    }
    fun deleteTask(id: Long) {
        viewModelScope.launch {
            repository.deleteTask(id)
        }
    }

    private fun saveTask() {
        viewModelScope.launch {
            val task = _state.value.task

            if (task.title.isBlank()) {
                _effect.emit(EditEffect.ShowError("Title cannot be empty"))
                return@launch
            }

            _state.update { it.copy(isLoading = true) }

            try {
                repository.updateTask(task)
                _effect.emit(EditEffect.TaskSaved)
            } catch (e: Exception) {
                _effect.emit(
                    EditEffect.ShowError(e.message ?: "Save failed")
                )
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}
