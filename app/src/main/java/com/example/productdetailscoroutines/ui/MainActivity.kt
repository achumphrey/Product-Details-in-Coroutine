package com.example.productdetailscoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productdetailscoroutines.R
import com.example.productdetailscoroutines.data.ProductModel
import com.example.productdetailscoroutines.di.DaggerProductComponent
import com.example.productdetailscoroutines.di.ProductRepositoryModule
import com.example.productdetailscoroutines.di.ProductWebservicesModule
import com.example.productdetailscoroutines.ui.adapter.ProductAdapter
import com.example.productdetailscoroutines.ui.adapter.ProductListener
import com.example.productdetailscoroutines.viewmodel.ProductViewModel
import com.example.productdetailscoroutines.viewmodel.ProductViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var productViewModelFactory: ProductViewModelFactory
    private lateinit var viewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter
    private var list = arrayListOf<ProductModel>()
    private var prodList = listOf<ProductModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerProductComponent.builder()
            .productWebservicesModule(ProductWebservicesModule())
            .productRepositoryModule(ProductRepositoryModule())
            .build()
            .inject(this)

        viewModel =
            ViewModelProviders.of(this, productViewModelFactory).get(ProductViewModel::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.fetchAndShowProdDetails()
        }
        prodList = viewModel.productDetail
        //    Log.i("MainActivity", "${viewModel.productDetails[0].productName}")
        productAdapter = ProductAdapter(prodList,
            object :
                ProductListener {
                override fun onProdClick(prodDetail: ProductModel) {
                    list.add(prodDetail)
                }
            }
        )
        rvProd.layoutManager = LinearLayoutManager(this)
        rvProd.adapter = productAdapter

        btnCompare.setOnClickListener {
            var textToDisplay = ""

            if (list.size == 2) {
                if (list[0].pId == list[1].pId && list[0].price == list[1].price) {
                    textToDisplay = "Both Products are the same"
                } else {
                    textToDisplay = "Both Products are different"
                }
            }else if  (list.size > 2) {
                textToDisplay = "Only two products can be selected"

            }else if (list.size < 2 && list.isNotEmpty()){
                textToDisplay = "You have less than two products"

            }else if (list.isEmpty())
                textToDisplay = "List is Empty"

            tvDisplay.text = textToDisplay

            list.clear()
        }

        btnReset.setOnClickListener {
            tvDisplay.text = ""
        }
    }

}
