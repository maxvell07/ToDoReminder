package malok.testtask.features.editTask.di


import malok.testtask.features.editTask.data.EditRepositoryImpl
import malok.testtask.features.editTask.domain.EditRepository
import malok.testtask.features.editTask.presentation.EditViewModel
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
