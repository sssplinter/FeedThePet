package com.kristina.feedthebeast.database.feedingData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feeding_table")
data class Feeding(

    @PrimaryKey(autoGenerate = true)
    var feedingId: Long = 0L,

    @ColumnInfo(name = "feeding_date_milli")
    var dateMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "feeding_score")
    var score: Int = -1,

    @ColumnInfo(name = "user_name")
    var userName: String = ""
)