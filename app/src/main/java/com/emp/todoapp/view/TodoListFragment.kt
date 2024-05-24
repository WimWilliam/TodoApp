package com.emp.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.emp.todoapp.R
import com.emp.todoapp.databinding.FragmentTodoListBinding
import com.emp.todoapp.viewmodel.ListViewTodoModel

class TodoListFragment : Fragment() {
    private lateinit var binding: FragmentTodoListBinding
    private val todoListAdapter  = TodoListAdapter(arrayListOf(), { item -> viewModel.clearTask(item) })
    private lateinit var viewModel:ListViewTodoModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return  binding.root

//        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewTodoModel::class.java)
        viewModel.refresh()

        binding.recViewTodo.layoutManager = LinearLayoutManager(context)
        binding.recViewTodo.adapter = todoListAdapter

        binding.btnFAB.setOnClickListener {
            val action = TodoListFragmentDirections.ActionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()


    }
    fun observeViewModel() {

        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            if (it.isEmpty()){
                binding.recViewTodo?.visibility = View.GONE
                binding.txtEror.setText("Your Todo Still Empty")
            }else{
                binding.recViewTodo?.visibility =View.VISIBLE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.progressLoad?.visibility = View.GONE
            } else {
                binding.progressLoad?.visibility = View.VISIBLE
            }
        })
        viewModel.todoLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.txtEror?.visibility = View.GONE
            } else {
                binding.txtEror?.visibility = View.VISIBLE
            }
        })



    }

}
