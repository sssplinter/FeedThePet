package com.kristina.feedthebeast.database.users

import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "user_name")
    var name: String = "name",

    @ColumnInfo(name = "score")
    var score: Int = 0
) {
}