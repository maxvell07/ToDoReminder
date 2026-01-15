package malok.todoreminder.features.createTask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import malok.todoreminder.domain.Task
import malok.todoreminder.features.createTask.domain.CreateRepository
import malok.todoreminder.features.createTask.presentation.model.CreateEffect
import malok.todoreminder.features.createTask.presentation.model.CreateIntent

class CreateViewModel(
    private val repository: CreateRepository
) : ViewModel() {

    private val _effect = MutableSharedFlow<CreateEffect>(
        replay = 0,
        extraBufferCapacity = 1
    )

    val effect = _effect.asSharedFlow()

    fun onIntent(intent: CreateIntent) {
        when (intent) {
            is CreateIntent.CreateTask -> createTask(intent.task)
        }
    }

    private fun createTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.createTask(task)
                _effect.emit(CreateEffect.TaskCreated)
            } catch (e: Exception) {
                _effect.emit(CreateEffect.ShowError(e.message ?: "Error creating task"))
            }
        }
    }
}