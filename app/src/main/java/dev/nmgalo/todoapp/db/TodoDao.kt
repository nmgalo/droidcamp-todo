package dev.nmgalo.todoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll(): List<TodoEntity>

    @Query("SELECT * FROM todo WHERE id = :todoId")
    fun getById(todoId: Int): TodoEntity

    @Insert
    fun insert(todoEntity: TodoEntity)

}
