package com.boo.sample.samplebackground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.boo.sample.samplebackground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.buttonExecutor.setOnClickListener {
            navController.navigate(R.id.executorFragment)
        }

        binding.buttonJob.setOnClickListener {
            navController.navigate(R.id.jobFragment)
        }

        binding.buttonWork.setOnClickListener {
            navController.navigate(R.id.workFragment)
        }

    }
}