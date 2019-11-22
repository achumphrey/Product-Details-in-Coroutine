package com.example.productdetailscoroutines.data.repository

import com.example.productdetailscoroutines.data.ProductModel

interface ProductRepository {
    suspend fun fetchProductRepos(): List<ProductModel>
}