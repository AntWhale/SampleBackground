package com.boo.sample.samplebackground

import android.os.Handler
import java.util.concurrent.Executor

class NumberRepository(val resultHandler: Handler, val executor: Executor) {


    fun longTask(callback : RepositoryCallback<Int>) {
        executor.execute {

            try {//background
                var num = 0
                for(count in 1..100) {
                    num++
                    //UI 갱신을 위해서 콜백
                    val result = Result.Success(num)
                    notifyResult(result, callback)
                    Thread.sleep(100)
                }
            } catch (e: Exception) {
                val result = Result.Error(e)
                notifyResult(result, callback)

            }

        }
    }

    private fun notifyResult(
        result: Result<Int>,
        callback: RepositoryCallback<Int>
    ) {
        resultHandler.post {
            callback.onComplete(result)
        }
    }

}

interface RepositoryCallback<T> {
    fun onComplete(result: Result<T>)
}