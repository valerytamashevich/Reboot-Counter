package pl.valery.counter.dao.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RebootEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long
)
