package com.kristina.feedthebeast.database.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0L,

    @ColumnInfo(name = "user_name")
    val name: String = "name"
) {
}