package com.emp.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.emp.todoapp.R
import com.emp.todoapp.databinding.FragmentCreateTodoAppBinding
import com.emp.todoapp.model.Todo
import com.emp.todoapp.viewmodel.DetailTodoViewModel

class CreateTodoAppFragment : Fragment() {

    private  lateinit var binding: FragmentCreateTodoAppBinding
    private lateinit var viewModel: DetailTodoViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateTodoAppBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_create_todo_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        binding.btnAdd.setOnClickListener {

            var radio =
                view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)

            var todo = Todo(binding.txtInputTodoTitle.text.toString(),
                binding.txtInputNotes.text.toString(), radio.tag.toString().toInt())



            val list = listOf(todo)
            viewModel.addTodo(list)


            Toast.makeText(view.context,"Data Added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}