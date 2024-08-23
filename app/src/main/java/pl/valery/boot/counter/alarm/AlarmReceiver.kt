package pl.valery.boot.counter.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import pl.valery.boot.counter.notification.NotificationHelper
import pl.valery.boot.counter.notification.NotificationMessageHelper
import javax.inject.Inject


@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var messageHelper: NotificationMessageHelper

    override fun onReceive(context: Context, intent: Intent) {
        compositeDisposable.add(
            messageHelper.getNotificationMessage()
                .doFinally { compositeDisposable.dispose() }
                .subscribe({ message ->
                    NotificationHelper(context).showNotification(
                        "Reboot Event Message",
                        message
                    )
                }, {
                    it.printStackTrace()
                })
        )
    }
}
