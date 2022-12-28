package com.example.egyfwd2

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.egyfwd2.AstroidDetailsInfo.retrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.Flow

class Repository(private val database:RoomDB) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun refresh() {
            try {
                val startDate = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val startFormatted = startDate.format(formatter)
                val endDate = LocalDate.now().plusDays(7)
                val endFormatted = endDate.format(formatter)

                var list: List<AstroidHolder> = arrayListOf()
                var data = ArrayList<AstroidHolder>()
                val x = retrofitService.getProperties(
                    startFormatted.toString(),
                    endFormatted.toString(),
                    "api_key_here"
                ).await()

                x.nearEarthObjects.entries.forEach {
                    it.value.map {
                        var c = AstroidHolder(
                            it.id,
                            it.name,
                            it.closeApproachDate.get(0).closeAprroachData,
                            it.absoluteMagnitude,
                            it.estimatedDiameter.diameterKG.DKG,
                            it.closeApproachDate.get(0).speed.velocityKPH,
                            it.closeApproachDate.get(0).distanceFromEarth.mdk,
                            it.isPotentiallyHazardous
                        )
                        data.add(c)
                    }
                    list = data
                    withContext(Dispatchers.IO) {
                        database.getAstroidDao.deleteAll()
                        database.getAstroidDao.insertData(list)
                    }
                }

            } catch (e: Exception) {
                println("failddddddddddddddddddddd rvvvv" + e)
            }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getToday(): kotlinx.coroutines.flow.Flow<List<AstroidHolder>> {
        val startDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy%MM%dd")
        val startFormatted = startDate.format(formatter)
         return database.getAstroidDao.getToday(startFormatted)
    }
    fun getWeek(): kotlinx.coroutines.flow.Flow<List<AstroidHolder>> {
        return database.getAstroidDao.getDetails()
    }
}