package malok.testtask.notification.di

import android.app.AlarmManager
import android.content.Context
import malok.testtask.notification.data.source.AndroidNotificationScheduler
import malok.testtask.notification.domain.NotificationScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val notificationModule = module {
    // AlarmManager
    single { androidContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager }

    single<NotificationScheduler> {
        AndroidNotificationScheduler(
            androidContext(),
            get()
        )
    }
}