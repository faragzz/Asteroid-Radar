package com.example.egyfwd2

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val ASTO_BASE_URL ="https://api.nasa.gov/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(ASTO_BASE_URL)
    .build()

interface AstroidDetails{

//    /neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY
//    https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY
    @GET("/neo/rest/v1/feed?")
    fun getProperties(@Query("start_date") sid:String?,
                      @Query("end_date") eid:String?,
                      @Query("api_key") api:String?
): Deferred<AsteroidData>
}
object AstroidDetailsInfo {
    val retrofitService : AstroidDetails by lazy {
        retrofit.create(AstroidDetails::class.java)
    }
}