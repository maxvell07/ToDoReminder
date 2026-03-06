package malok.testtask.edit_task.editTask.di


import malok.testtask.edit_task.editTask.data.EditRepositoryImpl
import malok.testtask.edit_task.editTask.domain.EditRepository
import malok.testtask.edit_task.editTask.presentation.EditViewModel
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
