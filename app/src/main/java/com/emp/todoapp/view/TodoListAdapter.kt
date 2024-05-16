package com.emp.todoapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emp.todoapp.databinding.TodoItemLayoutBinding
import com.emp.todoapp.model.Todo

class TodoListAdapter (val todoList:ArrayList<Todo>,
                       val adapterOnClick : (Todo) -> Unit):
RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>()

{
    class TodoViewHolder(var binding:TodoItemLayoutBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder
    {
        var binding = TodoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return  TodoViewHolder(binding)

    }

//    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
//        holder.binding.chkTask.text = todoList[position].title
//    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.chkTask.setText(todoList[position].title.toString())

        holder.binding.chkTask.setOnCheckedChangeListener {
            compoundButton, b ->
            if(compoundButton.isPressed) {
            adapterOnClick(todoList[position])
            }
        }
    }


    override fun getItemCount(): Int {
        return  todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}