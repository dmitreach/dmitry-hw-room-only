package com.example.dmitryhow.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.dmitryhow.R
import com.example.dmitryhow.databinding.FragmentTasksBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TasksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTasksBinding.inflate(inflater,container,false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        // build the database  - если не существует (это как в SQL базах - создать базу если не существует)
        // и получить на ссылку на свойство taskDao

        val viewModelFactory = TasksViewModelFactory (dao)
        val viewModel = ViewModelProvider (this,viewModelFactory).get(TasksViewModel::class.java)
        // получить viewModel
        binding.viewModel = viewModel
        // data binding связывает с fragment_tasks.xml
        // так чтобы fragment_tasks.xml может пользоваться свойствами и методами
        binding.lifecycleOwner = viewLifecycleOwner
        // Откликается на обновления - чтобы актуально было

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}