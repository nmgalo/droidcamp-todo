package dev.nmgalo.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val items = ArrayList<TodoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val todoRecyclerView = findViewById<RecyclerView>(R.id.todoRecyclerView)
        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TodoAdapter(items)
        val todoEditText = findViewById<EditText>(R.id.todoEditText)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {

            if (todoEditText.text.toString().isEmpty())
                Toast.makeText(this, "please enter todo", Toast.LENGTH_SHORT).show()
            else {
                items.add(0, TodoModel(todoEditText.text.toString()))
                adapter.notifyDataSetChanged()
                todoEditText.setText("")
            }

        }

        todoRecyclerView.adapter = adapter
    }

}