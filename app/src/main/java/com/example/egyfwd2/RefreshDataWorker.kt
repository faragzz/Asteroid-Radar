package com.example.egyfwd2

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.egyfwd2.RoomDB.Companion.getDataBase
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params:WorkerParameters):
            CoroutineWorker(appContext,params) {
    companion object{
        const val WORK_NAME ="RefreshDataWorker"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        val database = getDataBase(applicationContext)
        val repo = Repository(database)
        repo.refresh()
        return try {
            repo.refresh()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
