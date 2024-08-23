package pl.valery.boot.counter

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pl.valery.boot.counter.alarm.AlarmScheduler
import pl.valery.boot.counter.notification.NotificationMessageHelper
import pl.valery.boot.counter.view.RebootInfoAdapter
import pl.valery.counter.viewmodel.RebootViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RebootListActivity : AppCompatActivity(R.layout.activity_reboot_list) {

    private val viewModel: RebootViewModel by viewModels()

    @Inject
    lateinit var notificationMessageHelper: NotificationMessageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PermissionUtils.hasNotificationPermissions(this)) {
            PermissionUtils.requestNotificationPermission(supportFragmentManager)
        }

        AlarmScheduler().scheduleRepeatingAlarm(this)

        viewModel.rebootInfo.observe(this) { infoList ->
            findViewById<TextView>(R.id.reboot_info_no_item_message).isVisible =
                infoList.isEmpty()
            findViewById<RecyclerView>(R.id.reboot_info_list).adapter =
                RebootInfoAdapter(infoList)
        }
    }
}
