package com.kristina.feedthebeast.database

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
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
                    ).addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.d("BESISH", "DB CREATED")
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            Log.d("BESISH", "DB OEPEND")
                        }

                        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                            super.onDestructiveMigration(db)
                            Log.d("BESISH", "HUI")
                        }
                    }).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}