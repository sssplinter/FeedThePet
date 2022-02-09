package com.kristina.feedthebeast.database.achievements

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements_table")
data class Achievement(
    @PrimaryKey(autoGenerate = true)
    var achievementId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "Achievement",

    @ColumnInfo(name = "score_to_achieve")
    var score: Int = -1,

    @ColumnInfo(name = "achievement_date_milli")
    var dateMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name="user_id")
    var userName: String = ""
) {
}