package com.kristina.feedthebeast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kristina.feedthebeast.database.achievements.Achievement
import com.kristina.feedthebeast.database.feedingData.Feeding
import com.kristina.feedthebeast.database.users.User

@Database(
    entities = [User::class, Achievement::class, Feeding::class],
    version = 1,
    exportSchema = false
)
abstract class FeedTheBeastDatabase : RoomDatabase() {

    abstract val feedTheBeastDatabaseDao: FeedTheBeastDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: FeedTheBeastDatabase? = null

        fun getInstance(context: Context): FeedTheBeastDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FeedTheBeastDatabase::class.java,
                        "feed_the_beast_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}