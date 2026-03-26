package malok.todoreminder.di

import malok.testtask.core_data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import malok.testtask.core.domain.TaskRepository
import malok.testtask.core_data.repository.TaskRepositoryImpl

val coreModule = module {

    single<AppDatabase> {
        AppDatabase.getDatabase(androidContext())
    }

    single { get<AppDatabase>().taskDao() }

    single<TaskRepository> {
        TaskRepositoryImpl(get())
    }
}