package pl.valery.myapplication.notification

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotificationDismissEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
