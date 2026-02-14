package malok.todoreminder.features.createTask.di

import malok.todoreminder.features.createTask.data.CreateRepositoryImpl
import malok.todoreminder.features.createTask.domain.CreateRepository
import malok.todoreminder.features.createTask.presentation.CreateViewModel
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
