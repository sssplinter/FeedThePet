package com.kristina.feedthebeast.database.feedingData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FeedingDataDao {

    @Insert
    fun insertFeeding(feeding: Feeding)

    @Query("SELECT * FROM feeding_table WHERE user_id= :userId ORDER BY feeding_date_milli DESC")
    fun getUserFeedings(userId: Long) : LiveData<List<Feeding>>
}