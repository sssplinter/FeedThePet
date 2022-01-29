package com.kristina.feedthebeast.database.achievements

import androidx.room.*

@Dao
interface AchievementDao {

    @Insert
    fun insertAchievement(achievement: Achievement)

    @Delete
    fun deleteAchievement(achievement: Achievement)

    @Query("DELETE FROM achievements_table")
    fun deleteAllAchievements()

    @Update
    fun updateAchievement(achievement: Achievement)

}