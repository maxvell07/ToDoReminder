package malok.todoreminder.di

import androidx.room.Room
import malok.todoreminder.data.db.AppDatabase
import malok.todoreminder.features.createTask.data.CreateRepositoryImpl
import malok.todoreminder.features.createTask.domain.CreateRepository
import malok.todoreminder.features.createTask.presentation.CreateViewModel
import malok.todoreminder.features.listTasks.data.ListRepositoryImpl
import malok.todoreminder.features.listTasks.domain.ListRepository
import malok.todoreminder.features.detail.data.DetailRepositoryImpl
import malok.todoreminder.features.detail.domain.DetailRepository
import malok.todoreminder.features.detail.presentation.DetailViewModel
import malok.todoreminder.features.listTasks.presentation.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Room
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    single { get<AppDatabase>().taskDao() }

    // Repository List
    single<ListRepository> {
        ListRepositoryImpl(get())
    }
    //Create Repository
    single<CreateRepository> {
        CreateRepositoryImpl(get())
    }
    // Repository detail
    single<DetailRepository> {
        DetailRepositoryImpl(get())
    }

    // ViewModel
    viewModel {
        ListViewModel(
            repository = get()
        )
    }

    viewModel {
        CreateViewModel(
            repository = get()
        )
    }

    viewModel {
        DetailViewModel(
            repository = get()
        )
    }
}
