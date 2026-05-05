package malok.testtask.list_tasks.listTasks.di

import malok.testtask.list_tasks.listTasks.data.ListRepositoryImpl
import malok.testtask.list_tasks.listTasks.domain.ListRepository
import malok.testtask.list_tasks.listTasks.presentation.ListViewModel
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
