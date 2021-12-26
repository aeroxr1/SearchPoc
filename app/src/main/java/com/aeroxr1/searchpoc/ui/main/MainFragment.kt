package com.aeroxr1.searchpoc.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.aeroxr1.searchpoc.R
import com.aeroxr1.searchpoc.databinding.MainFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        binding.button.setOnClickListener {
            viewModel.pressGenerate1()
        }
        binding.button2.setOnClickListener {
            viewModel.pressGenerate2()
        }

        binding.searchPersonView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchString.value = newText
                return true
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        launchAndRepeatWithViewLifecycle{
            launch {
                viewModel.listOfPerson.collect {
                    it.forEach{
                        Log.v("MainFragment", "List ${it.name}")
                    }
                }

            }
        }

    }





}