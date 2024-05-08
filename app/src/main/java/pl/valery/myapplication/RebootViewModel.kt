package pl.valery.myapplication

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import pl.valery.myapplication.data.AppDatabase
import javax.inject.Inject

@HiltViewModel
class RebootViewModel @Inject constructor(
    database: AppDatabase,
) : ViewModel() {

    val rebootInfo: Flowable<String> = database
        .rebootEventDao()
        .getRebootCountsPerDay()
        .map {
            if (it.isEmpty()) "No boots detected"
            else it.joinToString("\n", "*") { reboot ->
                "${reboot.date} - ${reboot.count}"
            }
        }
        .subscribeOn(Schedulers.io())
}
