package com.example.shoppingapp

import MyAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ScaleGestureDetector
import android.view.WindowInsetsAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView
    lateinit var myAdapter:MyAdapter
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)
        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData=retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                var responseBody=response.body()
                val productArray=responseBody?.products

                myAdapter= productArray?.let { MyAdapter(this@MainActivity, it) }!!
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)


            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("onFailure","onFailure"+t.message)
            }



})
    }
}




















