package com.example.cobaapi.api.service

import com.example.cobaapi.api.model.ProductRespons
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    fun getAll(): Call<ProductRespons>
}