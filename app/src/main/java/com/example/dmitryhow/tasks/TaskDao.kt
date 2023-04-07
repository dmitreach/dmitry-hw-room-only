package com.example.dmitryhow.tasks

import androidx.lifecycle.LiveData
import androidx.room.*
import java.sql.RowId

@Dao
interface TaskDao {
   @Insert
   suspend fun insert (task:Task)
   // suspend для корутинов
   @Update
   suspend fun update (task:Task)
   @Delete
   suspend fun delete (task:Task)

   // ниже suspend не нужен так как они и так работают в bg
   @Query ("SELECT*FROM task_table WHERE taskId = :taskId")
   fun get (taskId: Long): LiveData<Task>

   @Query ("SELECT*FROM task_table ORDER BY taskId DESC")
   fun getAll (): LiveData<List<Task>>

}