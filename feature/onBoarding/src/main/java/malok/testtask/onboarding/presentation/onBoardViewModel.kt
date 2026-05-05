package malok.testtask.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import malok.testtask.core.domain.CompleteOnboardingUseCase
import malok.testtask.core.domain.SetThemeUseCase
class OnboardingViewModel(
    private val setThemeUseCase: SetThemeUseCase,
    private val completeOnboardingUseCase: CompleteOnboardingUseCase
) : ViewModel() {

    fun applyTheme(theme: String, onFinish: () -> Unit) {
        viewModelScope.launch {
            setThemeUseCase(theme)
            completeOnboardingUseCase()
            onFinish()
        }
    }
}