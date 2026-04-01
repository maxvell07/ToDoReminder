package malok.testtask.core.domain

import malok.testtask.core.domain.repositories.PrefRepository

class GetOnboardingStateUseCase(
    private val repo: PrefRepository
) {
    operator fun invoke() = repo.isOnboardingCompleted()
}

class SetThemeUseCase(
    private val repo: PrefRepository
) {
    suspend operator fun invoke(theme: String) {
        repo.setTheme(theme)
    }
}

class CompleteOnboardingUseCase(
    private val repo: PrefRepository
) {
    suspend operator fun invoke() {
        repo.completeOnboarding()
    }
}

class GetThemeUseCase(
    private val repo: PrefRepository
) {
    operator fun invoke() =repo.getTheme()
}