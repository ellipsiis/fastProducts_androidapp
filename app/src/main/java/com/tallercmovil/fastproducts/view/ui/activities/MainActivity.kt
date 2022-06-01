package com.tallercmovil.fastproducts.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import com.tallercmovil.fastproducts.view.adapter.Adapter
import com.tallercmovil.fastproducts.databinding.ActivityMainBinding
import com.tallercmovil.fastproducts.models.Product
import com.tallercmovil.fastproducts.models.ProductApi
import com.tallercmovil.fastproducts.models.ProductDetail
import com.tallercmovil.fastproducts.models.Product_Data
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val base_url = "https://www.serverbpw.com/"
    private val LOGTAG = "LOGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()/*init app run connection*/

        val productsApi: ProductApi = retrofit.create(ProductApi::class.java)

        val call:Call<ArrayList<Product>> = productsApi.getProducts("cm/2022-2/products.php")


        call.enqueue(object:Callback<ArrayList<Product>>{
            override fun onResponse(call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>) {
//                The server has connection
                Log.d(LOGTAG,"Response of server ${response.toString()}")
                Log.d(LOGTAG,"Data: ${response.body().toString()}")
                binding.pbConnection.visibility = View.INVISIBLE
                val tmpProduct: Product

                val data = ArrayList<Product_Data>()

                for(tmpProduct in response.body()!!)
                {
                    val productTMP = Product_Data(tmpProduct.id_sn!!.toLong(),"${tmpProduct.name_sn}","${tmpProduct.provider_sn}","${tmpProduct.price_sn}","${tmpProduct.thumbnail_sn}")
                    data.add(productTMP)
//                    Toast.makeText(this@MainActivity,"Nombre: ${tmpProduct.name_sn}",Toast.LENGTH_LONG).show()
                }


                binding.lv.adapter = Adapter(this@MainActivity, data)


            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Log.d(LOGTAG,"Error connection")
                Toast.makeText(this@MainActivity,"Without connection",Toast.LENGTH_LONG).show()
                binding.pbConnection.visibility = View.INVISIBLE
            }


        })
//        val data = ArrayList<Product_Data>()
//
//
//        for(i in 1 until 15){
//            val productTMP = Product_Data(i.toLong(),"title: ${i}","provider ${i}","price ${i}")
//            data.add(productTMP)
//        }




//        binding.lv.adapter = Adapter(this@MainActivity, data)

        binding.lv.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
//            i is the position where was click
//            Toast.makeText(this@MainActivity,"Position $i, ID: $l", Toast.LENGTH_LONG).show()

            val product = adapterView.adapter.getItem(i) as Product_Data

            val parametros = Bundle()

            parametros.putString("id",product.id.toString())

            Toast.makeText(this@MainActivity,"As Product_Data: ${product.id}",Toast.LENGTH_LONG).show()

            val intent = Intent(this@MainActivity,Detail::class.java)

            intent.putExtras(parametros)

            startActivity(intent)

//            Toast.makeText(this@MainActivity,"Click: title: ${product.title}", Toast.LENGTH_LONG).show()
        }
    }
}