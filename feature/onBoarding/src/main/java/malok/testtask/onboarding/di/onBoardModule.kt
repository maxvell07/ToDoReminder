package malok.testtask.onboarding.di

import malok.testtask.core.domain.CompleteOnboardingUseCase
import malok.testtask.core.domain.GetOnboardingStateUseCase
import malok.testtask.core.domain.GetThemeUseCase
import malok.testtask.core.domain.SetThemeUseCase
import malok.testtask.onboarding.presentation.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val onBoardModule = module {

    single { GetOnboardingStateUseCase(get()) }
    single { CompleteOnboardingUseCase(get()) }
    single { SetThemeUseCase(get()) }
    single { GetThemeUseCase(get()) }

    viewModel {
        OnboardingViewModel(
            get(),
            get()
        )
    }
}

