package dev.nmgalo.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.nmgalo.todoapp.db.AppDatabase
import dev.nmgalo.todoapp.db.TodoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val items = ArrayList<TodoModel>()
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val todoRecyclerView = findViewById<RecyclerView>(R.id.todoRecyclerView)
        todoRecyclerView.layoutManager = LinearLayoutManager(this)

        val addButton = findViewById<FloatingActionButton>(R.id.addButton)
        addButton.setOnClickListener {
            startActivity(Intent(applicationContext, AddActivity::class.java))
        }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo-data"
        ).build()


        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            items.addAll(db.userDao().getAll().map { it.toUIModel() })
        }
        adapter = TodoAdapter(items)
        adapter.notifyDataSetChanged()
        todoRecyclerView.adapter = adapter
    }

    private fun TodoEntity.toUIModel() = TodoModel(
        todoText = todoTitle,
        todoDescription = todoDescription
    )

}