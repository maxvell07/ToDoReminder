package malok.todoreminder.features.listTasks.di

import malok.todoreminder.features.listTasks.data.ListRepositoryImpl
import malok.todoreminder.features.listTasks.domain.ListRepository
import malok.todoreminder.features.listTasks.presentation.ListViewModel
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
