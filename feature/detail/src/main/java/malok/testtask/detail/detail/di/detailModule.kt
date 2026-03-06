package malok.testtask.detail.detail.di

import malok.testtask.detail.detail.data.DetailRepositoryImpl
import malok.testtask.detail.detail.domain.DetailRepository
import malok.testtask.detail.detail.presentation.DetailViewModel
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
