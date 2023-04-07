package com.example.dmitryhow.tasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "task_table")
data class Task (
    @PrimaryKey (autoGenerate = true)
    var taskId: Int = 0,
    @ColumnInfo (name = "task_name")
    var taskName: String = "",
    @ColumnInfo (name = "task_done")
    var taskDone: Boolean = false
)
