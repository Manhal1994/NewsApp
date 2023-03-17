package com.manhal.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.manhal.newsapp.data.dto.NewsResult
import com.manhal.newsapp.databinding.ActivityMainBinding
import com.manhal.newsapp.ui.adapters.HomeNewsAdapter
import com.manhal.newsapp.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    lateinit var  viewModel: MainActivityViewModel
    lateinit var  binding:ActivityMainBinding
    lateinit var  adapter :HomeNewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        setupList()

        viewModel.result.observe(this) {
            when (it) {
                is NewsResult.Success -> {

                    binding.progressBar.visibility= View.GONE
                    adapter.addItems(it.data!!)

                }
                is NewsResult.Loading -> {
                    binding.progressBar.visibility= View.VISIBLE

                }
                is NewsResult.DataError -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility= View.GONE

                }
                else -> {
                    binding.progressBar.visibility= View.GONE
                }
            }


        }

        viewModel.getTopArticles()
    }

    private fun setupList(){
        binding.rvHomeNews.layoutManager = LinearLayoutManager(this)
        adapter = HomeNewsAdapter()
        binding.rvHomeNews.adapter = adapter

    }

    }
