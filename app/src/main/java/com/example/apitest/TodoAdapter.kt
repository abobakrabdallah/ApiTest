package com.example.apitest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.apitest.models.TodoData
import com.systemira.tal3etsamak.databinding.ItemTodoBinding

class TodoAdapter(
) : ListAdapter<TodoData, TodoAdapter.TodoViewHolder>(diffCallback) {

    inner class TodoViewHolder(val binding: ItemTodoBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val item = getItem(position)
            checkBox.isChecked = item.completed
            tvNote.text = item.title
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TodoData>() {
            override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
                return oldItem == newItem
            }

        }
    }
}