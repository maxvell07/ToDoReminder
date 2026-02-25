package malok.testtask.features.detail.di

import malok.testtask.features.detail.data.DetailRepositoryImpl
import malok.testtask.features.detail.domain.DetailRepository
import malok.testtask.features.detail.presentation.DetailViewModel
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
