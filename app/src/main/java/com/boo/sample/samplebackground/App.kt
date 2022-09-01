package com.boo.sample.samplebackground

import android.app.Application
import android.os.Looper
import androidx.core.os.HandlerCompat
import java.util.concurrent.Executors

class App : Application() {
    val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()

    val executorService = Executors.newFixedThreadPool(NUMBER_OF_CORES)
    val mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper())
}