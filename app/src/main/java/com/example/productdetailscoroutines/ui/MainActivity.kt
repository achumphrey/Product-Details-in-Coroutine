package com.example.productdetailscoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var productViewModelFactory: ProductViewModelFactory
    private lateinit var viewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter
    private var selectedList = arrayListOf<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDaggerDependency()

        viewModel =
            ViewModelProviders.of(this, productViewModelFactory).get(ProductViewModel::class.java)

        viewModel.productListLifeData.observe(this, Observer {products ->
            productAdapter.updateItems(products)
        })

        productAdapter = ProductAdapter(
            mutableListOf(),
            object :
                ProductListener {
                override fun onProdClick(prodDetail: ProductModel) {
                    selectedList.add(prodDetail)
                }
            }
        )

        rvProd.layoutManager = LinearLayoutManager(this)
        rvProd.adapter = productAdapter

        btnCompare.setOnClickListener {
            var textToDisplay = ""

            if (selectedList.size == 2) {
                if (selectedList[0].pId == selectedList[1].pId && selectedList[0].price == selectedList[1].price) {
                    textToDisplay = "Both Products are the same"
                } else {
                    textToDisplay = "Both Products are different"
                }
            }else if  (selectedList.size > 2) {
                textToDisplay = "Only two products can be selected"

            }else if (selectedList.size < 2 && selectedList.isNotEmpty()){
                textToDisplay = "You have less than two products"

            }else if (selectedList.isEmpty())
                textToDisplay = "List is Empty"

            tvDisplay.text = textToDisplay

            selectedList.clear()
        }

        btnReset.setOnClickListener {
            tvDisplay.text = ""
        }

        viewModel.fetchProdDetails()
    }

    fun getDaggerDependency(){
        DaggerProductComponent.builder()
            .productWebservicesModule(ProductWebservicesModule())
            .productRepositoryModule(ProductRepositoryModule())
            .build()
            .inject(this)
    }
}
