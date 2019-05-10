package com.himanshurawat.githubdeveloperapi.ui.main

import android.content.Context
import com.himanshurawat.githubdeveloperapi.pojo.Users

class MainActivityPresenter(val view: MainActivityContract.View): MainActivityContract.Presenter{

    override fun requestData(after: Long) {
        model.fetch(after)
    }

    private lateinit var model: MainActivityContract.Model

    override fun initModel(context: Context) {
        model = MainActivityModel(context,this)
    }

    override fun dataLoaded(users: List<Users>) {
        for(item in users){
            view.addUser(item)
        }
    }


}