package com.example.productdetailscoroutines.di

import com.example.productdetailscoroutines.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProductWebservicesModule::class, ProductRepositoryModule::class])
interface ProductComponent {

    fun inject(mainActivity: MainActivity)
}