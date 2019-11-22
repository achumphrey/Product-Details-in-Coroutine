package com.example.productdetailscoroutines.data.repository

import com.example.productdetailscoroutines.data.ProductModel
import com.example.productdetailscoroutines.data.remote.ProductWebServices
import javax.inject.Inject

open class ProductRepositoryImpl @Inject constructor(private val webServices: ProductWebServices) : ProductRepository {
   override suspend fun fetchProductRepos(): List<ProductModel> {
        return webServices.fetchProdWebService()
    }
}