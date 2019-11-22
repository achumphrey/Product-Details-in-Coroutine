package com.example.productdetailscoroutines.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetailscoroutines.data.ProductModel
import com.example.productdetailscoroutines.ui.adapter.ProductListener
import kotlinx.android.synthetic.main.view_holder_product.view.*


class ProductViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    fun bindItem(
        prodDetails: ProductModel,
        listener: ProductListener
    ) {

        itemView.tvProdId.text = prodDetails.pId
        itemView.tvPrice.text = prodDetails.price.amount.rate
        itemView.tvProdName.text = prodDetails.productName
        itemView.tvProdDesc.text = prodDetails.descr
        itemView.tvMfgDate.text = prodDetails.mfgDate
        itemView.cbProd.isChecked = false

        itemView.cbProd.setOnClickListener {
            if (itemView.cbProd.isChecked)
            listener.onProdClick(prodDetails)
        }
    }
}