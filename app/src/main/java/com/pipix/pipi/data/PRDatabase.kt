package com.pipix.pipi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PureResult::class, SpeechResult::class, Old::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PRDatabase: RoomDatabase() {

    abstract fun pureResultDao(): PureResultDao

    companion object{
        private var INSTANCE: PRDatabase? = null

        fun getDatabase(context: Context): PRDatabase{

            val tempInstance= INSTANCE
            if( tempInstance != null) {
                return tempInstance
            }
            // safe multithreading
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PRDatabase::class.java,
                    "pr_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}