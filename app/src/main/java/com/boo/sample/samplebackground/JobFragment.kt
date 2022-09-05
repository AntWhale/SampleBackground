package com.boo.sample.samplebackground

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boo.sample.samplebackground.databinding.FragmentJobBinding

class JobFragment : Fragment() {
    private lateinit var binding: FragmentJobBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_job, container, false)
        binding = FragmentJobBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            JobService.enqueueWork(requireContext(), Intent())
        }
    }
}