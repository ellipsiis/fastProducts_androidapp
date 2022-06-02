package com.tallercmovil.fastproducts.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tallercmovil.fastproducts.databinding.ActivityDetailBinding
import com.tallercmovil.fastproducts.models.Product
import com.tallercmovil.fastproducts.models.ProductApi
import com.tallercmovil.fastproducts.models.ProductDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val base_url = "https://www.serverbpw.com/"
    private val LOGTAG = "LOGS"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id_product = bundle?.getString("id","0")
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()/*init app run connection*/

        val productsApi: ProductApi = retrofit.create(ProductApi::class.java)

        val call: Call<ProductDetail> = productsApi.getProductDetail(id_product)

        call.enqueue(object: Callback<ProductDetail>{
            override fun onResponse(call: Call<ProductDetail>, response: Response<ProductDetail>) {
                with(binding){
                    pbConnection2.visibility = View.INVISIBLE

                    Glide.with(this@Detail).
                            load(response.body()?.image_detail)
                            .into(ivThumbProduct)

                    tvTitleProduct.text = response.body()?.name_detail
                    tvLongDesc.text = response.body()?.desc_detail
                }
//                Toast.makeText(this@Detail,"Successful", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<ProductDetail>, t: Throwable) {
                Log.d(LOGTAG,"Error connection")
//                Toast.makeText(this@Detail,"Without connection", Toast.LENGTH_LONG).show()
                binding.pbConnection2.visibility = View.INVISIBLE
            }

        })
    }
}