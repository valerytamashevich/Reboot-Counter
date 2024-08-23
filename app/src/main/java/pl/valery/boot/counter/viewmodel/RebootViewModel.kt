package pl.valery.counter.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import pl.valery.counter.repository.IRebootEventRepository
import javax.inject.Inject

@HiltViewModel
class RebootViewModel @Inject constructor(
    private val repository: IRebootEventRepository,
) : ViewModel() {

    val rebootInfo: Flowable<String> = repository
        .rebootCountsPerDay
        .map {
            if (it.isEmpty()) "No boots detected"
            else it.joinToString("\n", "*") { reboot ->
                "${reboot.date} - ${reboot.count}"
            }
        }
        .subscribeOn(Schedulers.io())
}
