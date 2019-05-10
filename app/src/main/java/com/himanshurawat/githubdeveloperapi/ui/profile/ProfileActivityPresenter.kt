package com.himanshurawat.githubdeveloperapi.ui.profile

import android.content.Context
import com.himanshurawat.githubdeveloperapi.pojo.Repo
import com.himanshurawat.githubdeveloperapi.pojo.User

class ProfileActivityPresenter(val view: ProfileActivityContract.View): ProfileActivityContract.Presenter {
    override fun requestRepoData(username: String) {
        model.fetchRepo(username)
    }

    override fun repoLoaded(repo: MutableList<Repo>) {
        for(item in repo){
            view.updateRepo(item)
        }
    }

    private lateinit var model: ProfileActivityContract.Model

    override fun initModel(context: Context) {
        model = ProfileActivityModel(context,this)
    }

    override fun profileLoaded(user: User) {
        view.updateViews(user)
    }

    override fun requestProfileData(username: String) {
        model.fetch(username)
    }


}