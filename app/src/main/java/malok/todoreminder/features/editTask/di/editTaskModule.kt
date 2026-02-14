package malok.todoreminder.features.editTask.di

import malok.todoreminder.features.editTask.data.EditRepositoryImpl
import malok.todoreminder.features.editTask.domain.EditRepository
import malok.todoreminder.features.editTask.presentation.EditViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val editTaskModule = module {

    single<EditRepository> {
        EditRepositoryImpl(get())
    }

    viewModel { (taskId: String) ->
        EditViewModel(
            id = taskId,
            repository = get()
        )
    }
}
