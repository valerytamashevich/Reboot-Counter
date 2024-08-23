package pl.valery.boot.counter.notification

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.valery.boot.counter.dao.model.RebootEvent
import pl.valery.boot.counter.repository.IRebootEventRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NotificationMessageHelper @Inject constructor(
    private val repository: IRebootEventRepository,
) {

    fun getNotificationMessage(): Observable<String> {
        return repository.allRebootEvents
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { events ->
                when (events.size) {
                    0 -> "No boots detected"
                    1 -> "The boot was detected = ${formatDate(events.last().timestamp)}"
                    else -> "Last boots time delta = ${getLastTwoEventsTimeDelta(events)}"
                }
            }

    }

    private fun getLastTwoEventsTimeDelta(events: List<RebootEvent>): Long {
        return events.last().timestamp - events[events.size - 2].timestamp
    }

    private fun formatDate(timestamp: Long): String {
        return SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date(timestamp))
    }
}