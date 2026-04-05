package malok.testtask.onboarding.di

import malok.testtask.onboarding.presentation.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val onBoardModule = module {

    viewModel {
        OnboardingViewModel(
            get(),
            get()
        )
    }
}

