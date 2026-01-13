package malok.todoreminder.data.mappers

import malok.todoreminder.data.db.TaskEntity
import malok.todoreminder.domain.Task

fun TaskEntity.toDomain() =
    Task(id, title, description,isDone,date)

fun Task.toEntity() =
    TaskEntity(this.id?:0, title, description, isDone, date)