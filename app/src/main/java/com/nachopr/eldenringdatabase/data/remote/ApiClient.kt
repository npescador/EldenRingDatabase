package com.nachopr.eldenringdatabase.data.remote

import com.nachopr.eldenringdatabase.common.BASE_URL_SERVICE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SERVICE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}