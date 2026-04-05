package malok.testtask.profile.di

import malok.testtask.profile.presentation.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ProfileModule = module {

    viewModel {
        ProfileViewModel(
            getThemeUseCase = get(),
            setThemeUseCase = get()
        )
    }
}