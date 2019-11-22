package com.example.productdetailscoroutines.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetailscoroutines.ui.viewholder.ProductViewHolder
import com.example.productdetailscoroutines.R
import com.example.productdetailscoroutines.data.ProductModel
import com.example.productdetailscoroutines.utils.inflate


class ProductAdapter constructor(private val prodDetail: MutableList<ProductModel>, private val listener: ProductListener) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view: View = parent.inflate(R.layout.view_holder_product, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = prodDetail.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItem(prodDetail[holder.adapterPosition],listener)
    }

    fun updateItems(newProducts: List<ProductModel>, clearOldItems: Boolean = true){
        if (clearOldItems){
            prodDetail.clear()
        }
        prodDetail.addAll(newProducts)
        notifyDataSetChanged()//notify recyclerview when data changes
    }
}
