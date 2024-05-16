package com.emp.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.emp.todoapp.R
import com.emp.todoapp.databinding.FragmentCreateTodoAppBinding
import com.emp.todoapp.model.Todo
import com.emp.todoapp.viewmodel.DetailTodoViewModel

class CreateTodoAppFragment : Fragment() {

    private  lateinit var binding: FragmentCreateTodoAppBinding
    private lateinit var viewModel: DetailTodoViewModel
//    private lateinit var binding:FragmentCreateTodoBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            var todo = Todo(
                binding.txtInputTodoTitle.text.toString(),
                binding.txtInputNotes.text.toString()

            )
            val list = listOf(todo)
            viewModel.addTodo(list)
//            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()

            Toast.makeText(view.context,"Data Added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }







}