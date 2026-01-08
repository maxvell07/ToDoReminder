package malok.todoreminder.features.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import malok.todoreminder.domain.Task
import malok.todoreminder.features.detail.domain.DetailRepository

class DetailViewModel(
    private val repository: DetailRepository
) : ViewModel() {

    private val _state = MutableStateFlow<Task?>(null)
    val state: StateFlow<Task?> = _state

    fun getTaskById(id: Long){
        viewModelScope.launch {
            _state.value = repository.getTaskById(id)
        }
    }

    //fun change status task done or progress

}
