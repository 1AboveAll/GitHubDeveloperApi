package com.himanshurawat.githubdeveloperapi.ui.main

import android.content.Context
import com.himanshurawat.githubdeveloperapi.pojo.Users

interface MainActivityContract {

    interface Model{
        fun fetch(after: Long)
    }

    interface View{
        fun addUser(user: Users)
    }

    interface Presenter{
        fun initModel(context: Context)
        fun dataLoaded(users: List<Users>)
        fun requestData(after: Long)
    }
}