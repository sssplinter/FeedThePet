package com.kristina.feedthebeast.database.achievements

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_to_achievement")
data class UserToAchievement(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "user_id")
    val userId: Long = 0L,

    @ColumnInfo(name = "achievement_id")
    val achievementId: Long = 0L
) {
}