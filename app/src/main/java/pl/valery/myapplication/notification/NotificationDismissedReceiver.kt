package pl.valery.myapplication.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import pl.valery.myapplication.data.AppDatabase
import javax.inject.Inject

@AndroidEntryPoint
class NotificationDismissedReceiver : BroadcastReceiver() {

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onReceive(context: Context, intent: Intent) {
        val notificationId = intent.extras!!.getInt(NotificationHelper.DISMISS_NOTIFICATION_ID)

        if (notificationId == 123) {
            val notificationDatabase = appDatabase.notificationDismissDao()
            // todo add notification show time changing
        }
    }
}
