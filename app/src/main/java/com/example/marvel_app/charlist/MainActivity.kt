package com.example.marvel_app.charlist

import CustomAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel_app.R
import com.example.marvel_app.detailpage.DetailActivity
import com.example.marvel_app.service.RetrofitInstance

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel = MainViewModel(RetrofitInstance)

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var detailsIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        viewModel.mypost.observe(this, Observer { pagingData ->
            pagingData?.let { characters ->
                adapter = CustomAdapter(this, emptyList()) { character ->
                    val characterId = character.id
                    // Tıklanan karakterin ID'sini kullanarak istediğiniz işlemi gerçekleştirin
                    println("Tıklanan karakterin ID'si: $characterId")
                    detailsIntent = Intent(applicationContext, DetailActivity::class.java)
                    detailsIntent.putExtra("charId", characterId.toString())
                    startActivity(detailsIntent)
                }
                recyclerView.adapter = adapter

                adapter.setData(characters.data.results)
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapter
        }

        viewModel.isLoading.observe(this) { isLoading ->
            findViewById<ProgressBar>(R.id.progressBar).visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.isError.observe(this) { isError ->
            findViewById<TextView>(R.id.textView).visibility = if (isError) View.VISIBLE else View.GONE
        }
    }
}
