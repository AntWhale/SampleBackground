package com.boo.sample.samplebackground

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NumberRepository((application as App).mainThreadHandler, (application as App).executorService)
    val progressLiveData = MutableLiveData(0)

    fun longTask() {
        repository.longTask(object : RepositoryCallback<Int>{
            override fun onComplete(result: Result<Int>) {
                if(result is Result.Success){
                    progressLiveData.postValue(result.data)
                } else {

                }
            }
        })
    }
}