package malok.testtask.core.domain

data class Task(
    val id: Long?=null,
    val title: String ="",
    val description: String= "",
    val isDone: Boolean = false,
    val date: Long= 0
)