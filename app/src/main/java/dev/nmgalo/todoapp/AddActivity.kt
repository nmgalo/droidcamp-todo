package dev.nmgalo.todoapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import dev.nmgalo.todoapp.databinding.ActivityAddBinding
import dev.nmgalo.todoapp.db.AppDatabase
import dev.nmgalo.todoapp.db.TodoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityAddBinding.inflate(layoutInflater).root
        setContentView(view)
        ActivityAddBinding.bind(view).onViewBind()
    }

    private fun ActivityAddBinding.onViewBind() {

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo-data"
        ).build()

        addButton.setOnClickListener {
            val todoTitle = todoTitle.text.toString()
            val todoDescription = todoDescription.text.toString()

            if (todoTitle.isNotEmpty() && todoDescription.isNotEmpty()) {

                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().insert(
                        TodoEntity(
                            todoTitle = todoTitle,
                            todoDescription = todoDescription
                        )
                    )
                }


                onBackPressed()
            } else {
                Toast.makeText(baseContext, "Enter todo data", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
