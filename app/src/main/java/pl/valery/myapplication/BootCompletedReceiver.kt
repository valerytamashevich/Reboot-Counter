package pl.valery.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.schedulers.Schedulers
import pl.valery.myapplication.data.AppDatabase
import pl.valery.myapplication.data.RebootEvent
import javax.inject.Inject

@AndroidEntryPoint
class BootCompletedReceiver : BroadcastReceiver() {

    @Inject
    lateinit var database: AppDatabase

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == Intent.ACTION_BOOT_COMPLETED) {

            val timestamp = System.currentTimeMillis()
            val rebootEvent = RebootEvent(timestamp = timestamp)

            context?.let {
                val rebootEventDao = database.rebootEventDao()
                rebootEventDao.placeRebootEvent(rebootEvent)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        // Successfully saved the reboot event
                    }, { error ->
                        // Handle potential errors
                        error.printStackTrace()
                    })
            }
        }
    }
}
