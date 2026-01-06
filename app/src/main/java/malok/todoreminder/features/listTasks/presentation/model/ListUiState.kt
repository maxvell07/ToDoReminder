package malok.todoreminder.features.listTasks.presentation.model

data class ListUiState(
    val tasks: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
data class Item(
    val id: String,
    val name: String
)
