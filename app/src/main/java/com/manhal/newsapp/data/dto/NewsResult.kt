package com.manhal.newsapp.data.dto

sealed class NewsResult<T>(val data: T? = null, val errorCode: Int? = null,val message:String?=null) {

    class Success<T>(data: T) : NewsResult<T>(data)

    class Loading<T>() : NewsResult<T>(data = null)

    class DataError<T>(errorCode: Int?, message: String?) :
        NewsResult<T>(errorCode = errorCode, data = null, message = message)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorCode]"
            is Loading<T> -> "Loading"
        }
    }
}