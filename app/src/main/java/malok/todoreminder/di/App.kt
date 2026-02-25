package malok.todoreminder.di

import android.app.Application
import malok.testtask.create_task.createTask.di.createTaskModule
import malok.testtask.detail.detail.di.detailModule
import malok.testtask.edit_task.editTask.di.editTaskModule
import malok.testtask.list_tasks.listTasks.di.listTasksModule
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