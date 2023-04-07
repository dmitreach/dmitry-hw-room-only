package com.example.dmitryhow.tasks


import android.util.Log
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel (val dao: TaskDao): ViewModel() {
    var newTaskName = ""

    private val tasks = dao.getAll()
    val tasksString = Transformations.map (tasks) {
        tasks -> formatTasks (tasks)
        //Log.d ("dima","$tasks") (чтобы заработало - нужно сразу после -> поставить)
        //[Task(taskId=8, taskName=dmitry, taskDone=false), Task(taskId=7, taskName=hi, taskDone=false),]
        // выводит List <Task>, а нам нужно чтобы String был
    }

    fun addTask () {
        viewModelScope.launch {
            // режим courutine добавляет
            val task = Task ()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    var newTaskId = ""

    fun delTask () {
        viewModelScope.launch {

            val task = Task ()
            task.taskId = newTaskId.toInt()
            dao.delete(task)
        }
    }

    fun formatTasks (tasks: List <Task>): String {
        val value = tasks.fold ("") {
            // initial с чего будет начинаться текст
                str, item -> str + '\n' + formatTask (item)
            // типа как s++ - складывает предыдущее с последующим строки
            // результат одна большая строка
        }
        //Log.d ("Dima","$value")
        return value



    }

    fun formatTask (task: Task): String {
        // Log.d ("Dima","$task")
        // полученные данные из базы (один из ..)
        // Task (taskId=8, taskName=dmitry, taskDone=false)
        var str = "ID: ${task.taskId}"
        str += '\n' + "Name: ${task.taskName}"
        str += '\n' + "Complete: ${task.taskDone}" + '\n'
        return str
        // это то как уже будет показываться на экран для юзера
    }

}
