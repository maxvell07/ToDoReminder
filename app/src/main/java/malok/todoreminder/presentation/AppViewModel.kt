package malok.todoreminder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import malok.testtask.core.domain.GetOnboardingStateUseCase
import malok.testtask.core.domain.GetThemeUseCase
import malok.testtask.navigation.ListRoute

class AppViewModel(
    getOnboardingStateUseCase: GetOnboardingStateUseCase,
    getThemeUseCase: GetThemeUseCase
) : ViewModel() {

    val startDestination: StateFlow<ListRoute> = getOnboardingStateUseCase()
        .map { completed ->
            if (completed) ListRoute.ListScreen else ListRoute.Onboarding
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ListRoute.ListScreen
        )

    val theme: StateFlow<Boolean> = getThemeUseCase()
        .map { theme -> theme == "dark" }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )
}