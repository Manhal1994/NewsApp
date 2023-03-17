package com.manhal.newsapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manhal.newsapp.data.NewsRepository
import com.manhal.newsapp.data.database.entity.Article
import com.manhal.newsapp.data.dto.NewsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {
    private val _result = MutableLiveData<NewsResult<List<Article>>>()
    val result: LiveData<NewsResult<List<Article>>> get() = _result

    fun  getTopArticles(){
        viewModelScope.launch {
            repository.getTopArticles("home.json").collect{
                if(it is NewsResult.Success){
                    _result.value = it

                }
            }
        }

    }
}