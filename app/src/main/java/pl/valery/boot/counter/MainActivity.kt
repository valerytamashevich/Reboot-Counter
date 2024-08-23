package pl.valery.boot.counter

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import pl.valery.boot.counter.alarm.AlarmScheduler
import pl.valery.boot.counter.notification.NotificationMessageHelper
import pl.valery.counter.viewmodel.RebootViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: RebootViewModel by viewModels()

    @Inject
    lateinit var notificationMessageHelper: NotificationMessageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PermissionUtils.hasNotificationPermissions(this)) {
            PermissionUtils.requestNotificationPermission(supportFragmentManager)
        }

        AlarmScheduler().scheduleRepeatingAlarm(this)

        viewModel.rebootInfo.observe(this) { info ->
            findViewById<TextView>(R.id.rebootCount).text = info
        }
    }
}
