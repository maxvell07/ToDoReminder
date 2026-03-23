package malok.todoreminder.di

import androidx.room.Room
import malok.testtask.core_data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import malok.testtask.core.domain.TaskRepository
import malok.testtask.core_data.repository.TaskRepositoryImpl

val coreModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    single { get<AppDatabase>().taskDao() }

    single<TaskRepository> {
        TaskRepositoryImpl(get())
    }
}