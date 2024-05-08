package pl.valery.myapplication.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.valery.myapplication.data.AppDatabase
import pl.valery.myapplication.notification.NotificationMessageHelper

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvider {

    @Provides
    fun getDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun providesNotificationHelper(database: AppDatabase): NotificationMessageHelper {
        return NotificationMessageHelper(database)
    }
}
