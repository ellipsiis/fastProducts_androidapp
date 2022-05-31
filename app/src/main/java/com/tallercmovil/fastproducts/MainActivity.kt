package com.tallercmovil.fastproducts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.tallercmovil.fastproducts.databinding.ActivityMainBinding
import com.tallercmovil.fastproducts.models.Product

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = ArrayList<Product>()

        for(i in 1 until 15){
            val productTmp = Product(i.toLong(), "Title: $i", "Provider: $i","Price: $i")
            data.add(productTmp)
        }

        binding.lv.adapter = Adapter(this@MainActivity, data)

        binding.lv.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
//            i is the position where was click
//            Toast.makeText(this@MainActivity,"Position $i, ID: $l", Toast.LENGTH_LONG).show()

            val product = adapterView.adapter.getItem(i) as Product

            Toast.makeText(this@MainActivity,"Click: title: ${product.title}", Toast.LENGTH_LONG).show()
        }
    }
}