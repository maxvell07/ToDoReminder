package malok.testtask.features.createTask.di

import malok.testtask.features.createTask.data.CreateRepositoryImpl
import malok.testtask.features.createTask.domain.CreateRepository
import malok.testtask.features.createTask.presentation.CreateViewModel
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
