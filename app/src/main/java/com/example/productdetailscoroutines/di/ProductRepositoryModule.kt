package com.example.productdetailscoroutines.di

import com.example.productdetailscoroutines.data.repository.ProductRepositoryImpl
import com.example.productdetailscoroutines.viewmodel.ProductViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductRepositoryModule {
    @Provides
    @Singleton
    fun provideProductViewModelFactory(productRepository: ProductRepositoryImpl): ProductViewModelFactory{
        return ProductViewModelFactory(productRepository)
    }
}