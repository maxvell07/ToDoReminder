package malok.todoreminder.di

import androidx.datastore.core.DataStore
import malok.testtask.core_data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import malok.testtask.core.domain.repositories.TaskRepository
import malok.testtask.core_data.preferences.PreferencesDataStore
import malok.testtask.core_data.repository.TaskRepositoryImpl
import androidx.datastore.preferences.core.Preferences
import malok.testtask.core.domain.CompleteOnboardingUseCase
import malok.testtask.core.domain.GetOnboardingStateUseCase
import malok.testtask.core.domain.GetThemeUseCase
import malok.testtask.core.domain.SetThemeUseCase
import malok.testtask.core.domain.repositories.PrefRepository
import malok.testtask.core_data.preferences.dataStore
import malok.testtask.core_data.repository.PrefRepositoryImpl
import malok.todoreminder.presentation.AppViewModel
import org.koin.core.module.dsl.viewModel

val coreModule = module {

    single<AppDatabase> {
        AppDatabase.getDatabase(androidContext())
    }

    single<PrefRepository> {
        PrefRepositoryImpl(get())
    }

    viewModel<AppViewModel> {
        AppViewModel(
             get(),
            get()
        )
    }

    single { get<AppDatabase>().taskDao() }

    single<TaskRepository> {
        TaskRepositoryImpl(get())
    }
    single { GetOnboardingStateUseCase(get()) }
    single { CompleteOnboardingUseCase(get()) }
    single { SetThemeUseCase(get()) }
    single { GetThemeUseCase(get()) }

    single<DataStore<Preferences>> {
        androidContext().dataStore
    }

    single {
        PreferencesDataStore(get())
    }

}