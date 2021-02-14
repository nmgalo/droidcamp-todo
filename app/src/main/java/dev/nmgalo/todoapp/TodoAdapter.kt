package dev.nmgalo.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val items: ArrayList<TodoModel>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val todoItem = itemView.findViewById<TextView>(R.id.todoItem)
        fun onBind(item: TodoModel) {
            todoItem.text = item.todoText
            todoItem.setOnLongClickListener {
                items.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                true
            }
        }
    }
}