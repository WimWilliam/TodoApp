package com.emp.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.emp.todoapp.R
import com.emp.todoapp.databinding.FragmentCreateTodoAppBinding
import com.emp.todoapp.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment() {

    private lateinit var binding:FragmentCreateTodoAppBinding
    private lateinit var viewModel: DetailTodoViewModel


    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCreateTodoAppBinding.inflate(inflater,container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        binding.txtJudulTodo.text = "Edit Todo"
        binding.btnAdd.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        binding.btnAdd.setOnClickListener {
            val radio =
                view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
            viewModel.update(binding.txtInputTodoTitle.text.toString(),
                binding.txtInputNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }




    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            binding.txtInputTodoTitle.setText(it.title)
            binding.txtInputNotes.setText(it.notes)
            when(it.priority){
                1->binding.radioLow.isChecked =true
                2->binding.radioMedium.isChecked = true
                else->binding.radioHigh.isChecked = true
            }

        })
    }





}