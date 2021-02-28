package dev.nmgalo.todoapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "todo_title") val todoTitle: String,
    @ColumnInfo(name = "todo_description") val todoDescription: String,
)
