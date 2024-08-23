package pl.valery.counter.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import pl.valery.boot.counter.dao.model.RebootDayCount
import pl.valery.boot.counter.repository.IRebootEventRepository
import javax.inject.Inject

@HiltViewModel
class RebootViewModel @Inject constructor(
    repository: IRebootEventRepository,
) : ViewModel() {

    val rebootInfo: Flowable<List<RebootDayCount>> = repository
        .rebootCountsPerDay
        .subscribeOn(Schedulers.io())
}
