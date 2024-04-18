package com.example.cobaapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cobaapi.api.model.ProductRespons
import com.example.cobaapi.api.adapter.ProductAdapter
import com.example.cobaapi.api.model.Product
import com.example.cobaapi.api.service.ApiClient
import com.example.cobaapi.R
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(){
    private lateinit var  swipeRefresh: SwipeRefreshLayout
    private  lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ProductRespons>
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefresh = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.recycler_view)
        productAdapter = ProductAdapter { product -> productOnClick(product) }
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        swipeRefresh.setOnRefreshListener {
            getData()
        }
        getData()
    }
    private fun productOnClick(product: Product){
        Toast.makeText(applicationContext,product.description, Toast.LENGTH_SHORT).show()

    }
    private  fun getData(){
        swipeRefresh.isRefreshing = true

        call= ApiClient.productService.getAll()
        call.enqueue(object : retrofit2.Callback<ProductRespons>{
            @SuppressLint("NotifyDataChanged")
            override fun onResponse(call: Call<ProductRespons>, response: Response<ProductRespons>) {
                swipeRefresh.isRefreshing = false
                if (response.isSuccessful){
                    productAdapter.submitList(response.body()?.products)
                    productAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ProductRespons>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}