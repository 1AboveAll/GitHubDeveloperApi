package com.himanshurawat.githubdeveloperapi.utils

import android.content.Context
import android.net.ConnectivityManager


fun hasNetwork(context: Context): Boolean{
    var isNetworkAvailable = false
    val cm = context.
            applicationContext.
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = cm.activeNetworkInfo
    if(activeNetworkInfo?.isConnected as Boolean)
        isNetworkAvailable = true

    return isNetworkAvailable
}


fun cacheSizeConverter(cacheSizeInMegaByte: Int): Long{
    return (cacheSizeInMegaByte*1024*1024).toLong()
}