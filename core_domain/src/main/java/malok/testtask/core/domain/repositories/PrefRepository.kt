package malok.testtask.core.domain.repositories

import kotlinx.coroutines.flow.Flow

interface PrefRepository {
    fun isOnboardingCompleted(): Flow<Boolean>
    fun getTheme(): Flow<String>
    suspend fun setTheme(theme: String)
    suspend fun completeOnboarding()
}