package malok.todoreminder.features.createTask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import malok.todoreminder.domain.Task
import malok.todoreminder.features.createTask.domain.CreateRepository
import malok.todoreminder.features.listTasks.presentation.model.ListEffect

class CreateViewModel(
    private val repository: CreateRepository
) : ViewModel() {

    private val _effect = MutableSharedFlow<ListEffect>(
        replay = 0,
        extraBufferCapacity = 1
    )

    val effect = _effect.asSharedFlow()

    fun createTask(task: Task) {
        viewModelScope.launch {
            repository.createTask(task)
        }
    }
}