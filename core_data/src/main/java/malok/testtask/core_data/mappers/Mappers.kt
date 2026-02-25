package malok.testtask.core_data.mappers

import malok.testtask.core.domain.Task
import malok.testtask.core_data.db.TaskEntity

fun TaskEntity.toDomain() =
    Task(id, title, description, isDone, date)

fun Task.toEntity() =
    TaskEntity(this.id?:0, title, description, isDone, date)