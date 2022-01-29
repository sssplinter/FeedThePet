package com.kristina.feedthebeast.database.achievements

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserToAchievementUserDao {

    @Insert
    fun insertAchievementToUSer(userToAchievement: UserToAchievement)

    @Delete
    fun deleteAchievementToUSer(userToAchievement: UserToAchievement)

    @Update
    fun updateAchievementToUSer(userToAchievement: UserToAchievement)

    @Query("SELECT * FROM user_to_achievement WHERE user_id = :userId ORDER BY achievement_id ASC")
    fun getUserAchievements(userId: Long): LiveData<UserToAchievement>
}