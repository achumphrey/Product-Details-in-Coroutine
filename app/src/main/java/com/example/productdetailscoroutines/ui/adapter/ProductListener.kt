package com.example.productdetailscoroutines.ui.adapter

import com.example.productdetailscoroutines.data.ProductModel

interface ProductListener {
    fun onProdClick(prodDetail: ProductModel){
    }
}