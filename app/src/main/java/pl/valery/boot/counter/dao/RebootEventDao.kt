package pl.valery.boot.counter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import pl.valery.counter.dao.model.RebootDayCount
import pl.valery.counter.dao.model.RebootEvent

@Dao
interface RebootEventDao {
    @Insert
    fun placeRebootEvent(event: RebootEvent): Completable

    @Query("SELECT * FROM RebootEvent ORDER BY id DESC LIMIT 1")
    fun getLastRebootEvent(): Single<RebootEvent>

    @Query("SELECT * FROM RebootEvent")
    fun getAllRebootEvents(): Observable<List<RebootEvent>>

    @Query("SELECT strftime('%d/%m/%Y', timestamp / 1000, 'unixepoch', 'localtime') AS date, COUNT(*) AS count FROM RebootEvent GROUP BY date")
    fun getRebootCountsPerDay(): Flowable<List<RebootDayCount>>
}
