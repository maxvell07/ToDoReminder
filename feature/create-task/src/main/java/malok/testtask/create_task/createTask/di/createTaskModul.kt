package malok.testtask.create_task.createTask.di

import malok.testtask.create_task.createTask.data.CreateRepositoryImpl
import malok.testtask.create_task.createTask.domain.CreateRepository
import malok.testtask.create_task.createTask.presentation.CreateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val createTaskModule = module {

    single<CreateRepository> {
        CreateRepositoryImpl(get())
    }

    viewModel {
        CreateViewModel(
            repository = get()
        )
    }
}
