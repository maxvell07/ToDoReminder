package malok.todoreminder.data.mappers

import malok.todoreminder.data.db.TaskEntity
import malok.todoreminder.domain.Task

fun TaskEntity.toDomain() =
    Task(id, title, description,isDone,createdAt)