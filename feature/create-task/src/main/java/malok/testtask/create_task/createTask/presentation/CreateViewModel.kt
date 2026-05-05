package malok.testtask.create_task.createTask.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import malok.testtask.core.domain.Task
import malok.testtask.create_task.createTask.domain.CreateRepository
import malok.testtask.create_task.createTask.presentation.model.CreateEffect
import malok.testtask.create_task.createTask.presentation.model.CreateIntent
import malok.testtask.create_task.createTask.presentation.model.CreateUiState
import malok.testtask.notification.domain.NotificationScheduler

internal class CreateViewModel(
    private val repository: CreateRepository,
    private val scheduler: NotificationScheduler
) : ViewModel() {

    private val _state = MutableStateFlow(
        CreateUiState(
            isLoading = false,
            task = Task(date = System.currentTimeMillis())
        )
    )
    val state: StateFlow<CreateUiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreateEffect>(
        replay = 0,
        extraBufferCapacity = 1
    )

    val effect = _effect.asSharedFlow()

    fun onIntent(intent: CreateIntent) {
        when (intent) {
            is CreateIntent.CreateTask -> createTask()
            is CreateIntent.TitleChanged ->
                updateTask { it.copy(title = intent.value) }

            is CreateIntent.DescriptionChanged ->
                updateTask { it.copy(description = intent.value) }

            is CreateIntent.TimeChanged ->
                updateTask { it.copy(date = intent.value) }
        }
    }

    private fun updateTask(
        reducer: (Task) -> Task
    ) {
        _state.update {
            it.copy(task = reducer(it.task))
        }
    }

    private fun createTask() {

        viewModelScope.launch {
            val task = state.value.task

            if (task.title.isBlank()) {
                _effect.emit(CreateEffect.EmptyFields("Empty Fields"))
//                _state.update { it.copy(error = "Title is empty") }
                return@launch
            }

            _state.update { it.copy(isLoading = true, error = null) }

            try {
                // Сохраняем и получаем реальный ID из базы
                val newTaskId = repository.createTask(task)
                Log.d("ALARM_DEBUG", "Task saved with ID: $newTaskId") // ДОБАВИТЬ

                val savedTask = task.copy(id = newTaskId)

                if (savedTask.date > 0 && savedTask.date > System.currentTimeMillis()) {
                    try {
                        scheduler.schedule(savedTask)
                        Log.d("ALARM_DEBUG", "Scheduler.schedule() called successfully") // ДОБАВИТЬ
                    } catch (e: Exception) {
                        Log.e("ALARM_DEBUG", "Failed to schedule: ${e.message}") // ДОБАВИТЬ
                    }
                }
                _effect.emit(CreateEffect.TaskCreated("Task Created"))
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = e.message ?: "Create failed")
                }
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}