package com.himanshurawat.githubdeveloperapi

import android.app.Application
import com.himanshurawat.githubdeveloperapi.utils.InternetConnectivity

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        InternetConnectivity.init(this)
    }
}