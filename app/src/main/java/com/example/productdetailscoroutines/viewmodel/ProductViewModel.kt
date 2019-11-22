package com.example.productdetailscoroutines.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productdetailscoroutines.data.ProductModel
import com.example.productdetailscoroutines.data.repository.ProductRepository
import com.example.productdetailscoroutines.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ProductViewModel constructor(private val productRepository: ProductRepository) : ViewModel() {

    var productDetail:List<ProductModel> = listOf()

    suspend fun fetchProdDetails() : List<ProductModel> {

        return GlobalScope.async(Dispatchers.IO) {
            productRepository.fetchProductRepos()
            // return prodDetail
        }.await()
    }

    suspend fun fetchAndShowProdDetails() {
        val prod = fetchProdDetails() // fetch on IO thread
        showProd(prod) // back on UI thread
    }

    fun showProd(prod: List<ProductModel>) {
        this.productDetail = prod
        Log.i("ViewModelShow", "${productDetail[1].productName}")
    }
}