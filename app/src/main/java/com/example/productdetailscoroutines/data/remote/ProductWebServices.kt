package com.example.productdetailscoroutines.data.remote

import com.example.productdetailscoroutines.data.ProductModel
import com.example.productdetailscoroutines.utils.Constant
import retrofit2.http.GET


interface ProductWebServices {

    @GET(Constant.endpointUrl)
   suspend fun fetchProdWebService(): List<ProductModel>
}