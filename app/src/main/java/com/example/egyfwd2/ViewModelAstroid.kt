package com.example.egyfwd2

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.egyfwd2.RoomDB.Companion.getDataBase
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import java.lang.Appendable
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*
import javax.security.auth.callback.Callback

@RequiresApi(Build.VERSION_CODES.O)
class viewModelAstroid(application: Application) :AndroidViewModel(application) {


    private val database = getDataBase(application)
    private val roRepository = Repository(database)

    private val _asteroid:MutableLiveData<List<AstroidHolder>> = MutableLiveData()
    val Rlist:LiveData<List<AstroidHolder>> = _asteroid

    init {
        getWeek()
        update()
    }

    fun getThisDay(){
        viewModelScope.launch {
            roRepository.getToday().collect{
                _asteroid.value = it
            }
        }
    }
    fun getWeek(){
        viewModelScope.launch{
            roRepository.getWeek().collect {
                _asteroid.value = it
            }
        }
    }
    private fun update() {
        viewModelScope.launch {
            roRepository.refresh()
        }
    }
    fun getSaveed(){
        viewModelScope.launch{
            roRepository.getWeek().collect {
                _asteroid.value = it
            }
        }
    }

}

