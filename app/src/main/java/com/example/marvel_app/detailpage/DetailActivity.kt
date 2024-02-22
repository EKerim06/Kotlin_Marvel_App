package com.example.marvel_app.detailpage

import ComicResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.marvel_a.DetailViewModel
import com.example.marvel_app.R

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var mlistView: ListView

    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var id:String

    private lateinit var detailData:ComicResult


    private lateinit var intent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent = getIntent()

        id = intent.getStringExtra("charId") ?: "1011334" // default value

        mlistView = findViewById(R.id.comicsListView)

        viewModel.fetchDetail(id)

        viewModel.detailData.observe(this, Observer { dtl->
            dtl?.let {
                detailData = dtl
                println("gelen karakterin idsi :${detailData.data!!.results?.get(0)!!.characters!!.items!![0].resourceURI} bunun sonundaki id")
                val imageView = findViewById<ImageView>(R.id.detailImageView)
                val charNameText =  findViewById<TextView>(R.id.charNameText)
                val descriptionText = findViewById<TextView>(R.id.descriptionBodyTextView)
                Glide.with(this)

                    .load(dtl.data?.results?.get(0)?.thumbnail?.path +"."+ dtl.data?.results?.get(0)?.thumbnail?.extension)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(imageView)

                charNameText.text = dtl.data?.results?.get(0)?.title ?: "title Null"
                descriptionText.text = dtl.data?.results?.get(0)?.textObjects?.get(0)?.text ?: "description Null"

            }
        })

        viewModel.filteredList.observe(this) { fltrdList ->
            println("======================= ${fltrdList.size}")
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fltrdList)
            mlistView.adapter = adapter

        }

        viewModel.isLoading.observe(this) { isLoading ->
            // Yükleme durumunu gözlemleyin
            findViewById<ProgressBar>(R.id.circle_Progress_Bar).isVisible = isLoading
        }

        viewModel.isError.observe(this) { isError ->
            // Hata durumunu gözlemleyin
            findViewById<TextView>(R.id.errorText).isVisible = isError
        }

    }


}