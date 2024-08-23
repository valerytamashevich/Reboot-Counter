package pl.valery.boot.counter.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import pl.valery.boot.counter.dao.RebootEventDao
import pl.valery.boot.counter.dao.model.RebootDayCount
import pl.valery.boot.counter.dao.model.RebootEvent
import javax.inject.Inject

interface IRebootEventRepository {
    val allRebootEvents: Observable<List<RebootEvent>>
    val rebootCountsPerDay: Flowable<List<RebootDayCount>>
    fun placeRebootEvent(event: RebootEvent): Completable
}

class RebootEventRepository @Inject constructor(
    private val rebootEventDao: RebootEventDao
) : IRebootEventRepository {

    override val allRebootEvents: Observable<List<RebootEvent>> =
        rebootEventDao.getAllRebootEvents()
    override val rebootCountsPerDay: Flowable<List<RebootDayCount>> =
        rebootEventDao.getRebootCountsPerDay()

    override fun placeRebootEvent(event: RebootEvent): Completable {
        return rebootEventDao.placeRebootEvent(event)
    }
}
