package malok.todoreminder.features.detail.di

import malok.todoreminder.features.detail.data.DetailRepositoryImpl
import malok.todoreminder.features.detail.domain.DetailRepository
import malok.todoreminder.features.detail.presentation.DetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {

    single<DetailRepository> {
        DetailRepositoryImpl(get())
    }

    viewModel { (taskId: String) ->
        DetailViewModel(
            id = taskId,
            repository = get()
        )
    }
}
