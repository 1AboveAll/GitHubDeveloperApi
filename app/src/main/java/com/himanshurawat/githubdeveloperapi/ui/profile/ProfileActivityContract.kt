package com.himanshurawat.githubdeveloperapi.ui.profile

import android.content.Context
import com.himanshurawat.githubdeveloperapi.pojo.Repo
import com.himanshurawat.githubdeveloperapi.pojo.User

interface ProfileActivityContract {

    interface Model{
        fun fetch(username: String)
        fun fetchRepo(username: String)
    }

    interface View{
        fun updateViews(user: User)
        fun updateRepo(repo: Repo)
    }


    interface Presenter{
        fun initModel(context: Context)
        fun profileLoaded(user: User)
        fun requestProfileData(username: String)
        fun requestRepoData(username: String)
        fun repoLoaded(repo: MutableList<Repo>)
    }

}