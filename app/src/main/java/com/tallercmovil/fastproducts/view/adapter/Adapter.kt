package com.tallercmovil.fastproducts.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.tallercmovil.fastproducts.databinding.ElementListviewBinding
import com.tallercmovil.fastproducts.models.Product
import com.tallercmovil.fastproducts.models.Product_Data

class Adapter(private val context: Context, val data: ArrayList<Product_Data>): BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ElementListviewBinding.inflate(inflater)

        with(binding){
            Glide.with(context)
                .load(data[p0].thumb)
                .into(ivThumbnail)
            tvTitle.text = data[p0].title
            tvProvider.text = data[p0].provider
            tvPrice.text = data[p0].price
        }

        return binding.root

    }

}