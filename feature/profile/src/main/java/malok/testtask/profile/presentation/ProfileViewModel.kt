package malok.testtask.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import malok.testtask.core.domain.GetThemeUseCase
import malok.testtask.core.domain.SetThemeUseCase

class ProfileViewModel(
    getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
): ViewModel() {

    val theme: StateFlow<Boolean> = getThemeUseCase()
        .map { it == "dark" }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun selectModeTheme(isChecked: Boolean) {
        viewModelScope.launch {
            if (isChecked){
                setThemeUseCase("dark")
            }else{
                setThemeUseCase("light")
            }
        }
    }
}