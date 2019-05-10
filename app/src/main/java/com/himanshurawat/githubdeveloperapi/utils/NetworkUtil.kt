package com.himanshurawat.githubdeveloperapi.utils

import android.content.Context
import android.net.ConnectivityManager


fun isNetworkAvailable(context: Context?): Boolean{
    if (context == null){
        return true
    }else{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}


fun cacheSizeConverter(cacheSizeInMegaByte: Int): Long{
    return (cacheSizeInMegaByte*1024*1024).toLong()
}