package pl.valery.boot.counter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.valery.boot.counter.notification.NotificationDao
import pl.valery.boot.counter.notification.NotificationDismissEvent
import pl.valery.counter.dao.model.RebootEvent

@Database(entities = [RebootEvent::class, NotificationDismissEvent::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rebootEventDao(): RebootEventDao
    abstract fun notificationDismissDao(): NotificationDao
}
