package malok.testtask.core_data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class PreferencesDataStore(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val THEME_KEY = stringPreferencesKey("theme")
        val ONBOARDING_KEY = booleanPreferencesKey("onboarding_completed")
    }

    val onboardingCompleted: Flow<Boolean> =
        dataStore.data.map { prefs ->
            prefs[ONBOARDING_KEY] ?: false
        }

    val theme: Flow<String> =
        dataStore.data.map { prefs ->
            prefs[THEME_KEY] ?: "light"
        }

    suspend fun setTheme(theme: String) {
        dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme
        }
    }

    suspend fun setOnboardingCompleted() {
        dataStore.edit { prefs ->
            prefs[ONBOARDING_KEY] = true
        }
    }
}

