package pl.valery.boot.counter.notification

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.Completable
import pl.valery.boot.counter.dao.model.RebootEvent

@Dao
interface NotificationDao {
    @Insert
    fun placeDismissEvent(event: RebootEvent): Completable
}