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

    var productListLifeData: MutableLiveData<List<ProductModel>> = MutableLiveData()

    fun fetchProdDetails() {

        GlobalScope.launch(Dispatchers.IO) {
            productListLifeData.postValue(productRepository.fetchProductRepos())
        }
    }
}