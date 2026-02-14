package malok.todoreminder.di

import android.app.Application
import malok.todoreminder.features.createTask.di.createTaskModule
import malok.todoreminder.features.detail.di.detailModule
import malok.todoreminder.features.editTask.di.editTaskModule
import malok.todoreminder.features.listTasks.di.listTasksModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                coreModule,
                createTaskModule,
                listTasksModule,
                detailModule,
                editTaskModule
            )
        }
    }
}