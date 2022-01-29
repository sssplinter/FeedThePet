package com.kristina.feedthebeast.database.users

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM users_table WHERE user_name = :userName ORDER BY userId ASC LIMIT 1")
    fun getUserByName(userName: String) : User?
}