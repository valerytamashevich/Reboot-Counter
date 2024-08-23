package pl.valery.boot.counter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.toLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Observable<T>.observe(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    return toFlowable(BackpressureStrategy.BUFFER).observe(lifecycleOwner, observer)
}

fun <T> Flowable<T>.observe(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    return toLiveData().observe(lifecycleOwner, observer)
}
