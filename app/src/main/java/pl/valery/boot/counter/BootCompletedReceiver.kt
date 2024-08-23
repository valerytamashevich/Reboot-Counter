package pl.valery.boot.counter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.valery.counter.dao.model.RebootEvent
import pl.valery.counter.repository.IRebootEventRepository
import javax.inject.Inject

@AndroidEntryPoint
class BootCompletedReceiver : BroadcastReceiver() {

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var repository: IRebootEventRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val rebootEvent = RebootEvent(timestamp = System.currentTimeMillis())

            context?.let {
                compositeDisposable.add(
                    repository.placeRebootEvent(rebootEvent)
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            // Successfully saved the reboot event
                        }, { error ->
                            // Handle potential errors
                            error.printStackTrace()
                        })
                )
            }
        }
    }
}
