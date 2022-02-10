package com.kristina.feedthebeast.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kristina.feedthebeast.database.achievements.Achievement
import com.kristina.feedthebeast.database.feedingData.Feeding
import com.kristina.feedthebeast.database.users.User

@Dao
interface FeedTheBeastDatabaseDao {

    @Insert
    fun insertFeeding(feeding: Feeding)

    @Insert
    fun insertFeeding(feedingList: List<Feeding>) : List<Long>

    @Insert
    fun insertUser(user: User)

    @Insert
    fun insertAchievement(achievement: Achievement)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM users_table WHERE user_name = :userName ORDER BY userId ASC LIMIT 1")
    fun getUserByName(userName: String): User?

    @Query("SELECT score FROM users_table WHERE userId =:userId ORDER BY userId ASC LIMIT 1")
    fun getUserScore(userId: Long): Int?

    @Query("SELECT * FROM feeding_table WHERE user_name= :userName ORDER BY feeding_date_milli DESC")
    fun getUserFeedings(userName: String): LiveData<List<Feeding>>

    @Query("SELECT * FROM feeding_table ORDER BY feeding_date_milli DESC")
    fun getAllFeedings(): LiveData<List<Feeding>>

    @Query("SELECT * FROM achievements_table ORDER BY achievementId ASC")
    fun getAllAchievements(): LiveData<List<Achievement>>

    @Query("SELECT * FROM users_table ORDER BY userId ASC")
    fun getAllUsers(): LiveData<List<User>>
}