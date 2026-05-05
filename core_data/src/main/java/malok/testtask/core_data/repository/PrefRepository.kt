package malok.testtask.core_data.repository

import malok.testtask.core.domain.repositories.PrefRepository
import malok.testtask.core_data.preferences.PreferencesDataStore

class PrefRepositoryImpl(
    private val prefs: PreferencesDataStore
) : PrefRepository {

    override fun isOnboardingCompleted() =
        prefs.onboardingCompleted

    override fun getTheme() =
        prefs.theme

    override suspend fun setTheme(theme: String){
        prefs.setTheme(theme)
    }

    override suspend fun completeOnboarding() {
        prefs.setOnboardingCompleted()
    }
}