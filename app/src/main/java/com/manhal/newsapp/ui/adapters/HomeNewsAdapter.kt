package com.manhal.newsapp.ui.adapters
import com.manhal.newsapp.BR

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manhal.newsapp.data.database.entity.Article
import com.manhal.newsapp.databinding.HomeNewsItemBinding

class HomeNewsAdapter() :RecyclerView.Adapter<HomeNewsAdapter.ViewHolder>() {
    val homeNews:MutableList<Article> = mutableListOf()

    inner class ViewHolder(private val binding:  HomeNewsItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.setVariable(BR.article,article)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val  binding = HomeNewsItemBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article =  homeNews[position]
        holder.bind(article)


    }
    fun addItems(articles: List<Article>) {
        this.homeNews.addAll(articles)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return homeNews.size

    }
}