package com.example.shoppingapp

import android.telecom.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getProductData(): retrofit2.Call<MyData>


}