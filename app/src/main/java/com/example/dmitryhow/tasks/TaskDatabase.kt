package com.example.dmitryhow.tasks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Task::class], version = 1, exportSchema = true )
abstract class TaskDatabase : RoomDatabase (){
    abstract val taskDao: TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase ? = null

        fun getInstance (context: Context): TaskDatabase  {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "task_database"
                    // Так будет называться база если она не существует
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}