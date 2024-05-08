package pl.valery.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RebootEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long
)
