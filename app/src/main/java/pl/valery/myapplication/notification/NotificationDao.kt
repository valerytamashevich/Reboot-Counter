package pl.valery.myapplication.notification

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.Completable
import pl.valery.myapplication.data.RebootEvent

@Dao
interface NotificationDao {
    @Insert
    fun placeDismissEvent(event: RebootEvent): Completable
}