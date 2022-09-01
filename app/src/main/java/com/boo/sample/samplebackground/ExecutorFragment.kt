package com.boo.sample.samplebackground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.boo.sample.samplebackground.databinding.FragmentExecutorBinding


class ExecutorFragment : Fragment() {
    private val viewModel : MainViewModel by lazy { ViewModelProvider(
        requireActivity(),
        ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
    ).get(MainViewModel::class.java) }

    private lateinit var binding: FragmentExecutorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_executor, container, false)
        binding = FragmentExecutorBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            viewModel.longTask()
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner){ progress ->
            binding.progress.progress = progress
        }

    }
}