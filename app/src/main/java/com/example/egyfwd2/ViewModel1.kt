package com.example.egyfwd2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.space.imageDetails
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ViewModel1 : ViewModel(){
    var url = MutableLiveData<String>()
    var title = MutableLiveData<String>()
    init {
        viewModelScope.launch {
            getAstroidInfo()
        }
    }
    suspend fun getAstroidInfo(){

            var getPropertiesDeferredImage = imageDetails.imageretrofitService.getProperties()
            try{
                var listResult = getPropertiesDeferredImage.await()
                url.value = listResult.url
                println("niceeeeeeeeeeeeeeeeee"+listResult.toString())
                title.value = listResult.title
            }catch (e:Exception){
                println("Image API Faileddddddddddddddddddddddddddddddddddddddddddddd"+e)
            }

    }
    fun geturl(): String? {
        return url.value
    }
}