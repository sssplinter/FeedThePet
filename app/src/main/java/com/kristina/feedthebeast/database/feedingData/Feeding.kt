package com.kristina.feedthebeast.database.feedingData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feeding_table")
data class Feeding(

    @PrimaryKey(autoGenerate = true)
    val feedingId: Long = 0L,

    @ColumnInfo(name = "feeding_date_milli")
    val dateMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "feeding_score")
    val score: Int = -1,

    @ColumnInfo(name = "user_id")
    val userId: Long = 0L
)