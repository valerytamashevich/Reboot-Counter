package pl.valery.boot.counter.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.valery.boot.counter.data.AppDatabase
import pl.valery.boot.counter.data.RebootEventDao
import pl.valery.boot.counter.notification.NotificationMessageHelper
import pl.valery.counter.repository.IRebootEventRepository
import pl.valery.counter.repository.RebootEventRepository

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvider {

    @Provides
    fun providesRebootEventRepository(rebootEventDao: RebootEventDao): IRebootEventRepository {
        return RebootEventRepository(rebootEventDao)
    }

    @Provides
    fun providesRebootEventDao(database: AppDatabase): RebootEventDao {
        return database.rebootEventDao()
    }

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun providesNotificationHelper(rebootEventRepository: IRebootEventRepository): NotificationMessageHelper {
        return NotificationMessageHelper(rebootEventRepository)
    }
}
