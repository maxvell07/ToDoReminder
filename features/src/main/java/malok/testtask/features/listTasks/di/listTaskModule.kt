package malok.testtask.features.listTasks.di

import malok.testtask.features.listTasks.data.ListRepositoryImpl
import malok.testtask.features.listTasks.domain.ListRepository
import malok.testtask.features.listTasks.presentation.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val listTasksModule = module {

    single<ListRepository> {
        ListRepositoryImpl(get())
    }

    viewModel {
        ListViewModel(
            repository = get()
        )
    }
}
