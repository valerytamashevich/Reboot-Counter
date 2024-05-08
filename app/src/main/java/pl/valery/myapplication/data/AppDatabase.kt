package pl.valery.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.valery.myapplication.notification.NotificationDao
import pl.valery.myapplication.notification.NotificationDismissEvent

@Database(entities = [RebootEvent::class, NotificationDismissEvent::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rebootEventDao(): RebootEventDao
    abstract fun notificationDismissDao(): NotificationDao
}
