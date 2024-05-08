package pl.valery.myapplication.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import pl.valery.myapplication.notification.NotificationHelper
import pl.valery.myapplication.notification.NotificationMessageHelper
import javax.inject.Inject


@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var messageHelper: NotificationMessageHelper

    override fun onReceive(context: Context, intent: Intent) {
        messageHelper.getNotificationMessage()
            .subscribe({ message ->
                NotificationHelper(context).showNotification(
                    "Reboot Event Message",
                    message
                )
            }, {
                it.printStackTrace()
            })
    }
}
