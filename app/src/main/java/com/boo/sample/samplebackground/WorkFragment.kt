package com.boo.sample.samplebackground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.boo.sample.samplebackground.databinding.FragmentWorkBinding


class WorkFragment : Fragment() {
    private lateinit var binding : FragmentWorkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_work, container, false)
        binding = FragmentWorkBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = OneTimeWorkRequest
            .Builder(MyWorker::class.java)
            .build()

        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(request.id).observe(viewLifecycleOwner){ workInfo ->
            val progress = workInfo.progress.getInt("progress", 0)
            binding.progress.progress = progress
        }

        binding.button.setOnClickListener {


            WorkManager.getInstance(requireContext())
                .enqueue(request)
        }
    }
}