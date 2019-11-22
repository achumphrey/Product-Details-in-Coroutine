package com.example.productdetailscoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productdetailscoroutines.data.ProductModel
import com.example.productdetailscoroutines.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProductViewModel constructor(private val productRepository: ProductRepository) : ViewModel() {

    var productListLifeData: MutableLiveData<List<ProductModel>> = MutableLiveData()

    fun fetchProdDetails() {

        GlobalScope.launch(Dispatchers.IO) {
            productListLifeData.postValue(productRepository.fetchProductRepos())
            //postValue notifies the main thread about change in value, that is
            // lifeData has changed the value
        }
    }
}