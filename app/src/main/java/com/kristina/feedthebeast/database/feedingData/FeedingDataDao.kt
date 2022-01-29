package com.kristina.feedthebeast.database.feedingData

import androidx.room.Insert
import androidx.room.Query

interface FeedingDataDao {

    @Insert
    fun insertFeeding(feeding: Feeding)

    @Query("SELECT * FROM feeding_table WHERE user_id= :userId ORDER BY feeding_date_milli DESC")
    fun getUserFeedings(userId: Long) :List<Feeding>
}