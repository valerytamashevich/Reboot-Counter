package pl.valery.counter.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import pl.valery.counter.dao.model.RebootDayCount
import pl.valery.counter.repository.IRebootEventRepository
import javax.inject.Inject

@HiltViewModel
class RebootViewModel @Inject constructor(
    private val repository: IRebootEventRepository,
) : ViewModel() {

    val rebootInfo: Flowable<List<RebootDayCount>> = repository
        .rebootCountsPerDay
        .subscribeOn(Schedulers.io())
}
