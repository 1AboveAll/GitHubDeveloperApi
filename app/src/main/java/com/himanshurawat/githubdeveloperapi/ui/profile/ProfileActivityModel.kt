package com.himanshurawat.githubdeveloperapi.ui.profile

import android.content.Context
import com.himanshurawat.githubdeveloperapi.network.NetworkClient
import com.himanshurawat.githubdeveloperapi.network.NetworkService
import com.himanshurawat.githubdeveloperapi.pojo.Repo
import com.himanshurawat.githubdeveloperapi.pojo.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit

class ProfileActivityModel(val context: Context, val presenter: ProfileActivityContract.Presenter): ProfileActivityContract.Model {


    private var retrofit: Retrofit = NetworkClient.getRetrofit(context)
    private var userService = retrofit.create(NetworkService::class.java)

    override fun fetch(username: String) {

        var user: User? = null

        userService.getUser(username).
            subscribeOn(io.reactivex.schedulers.Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<User> {
            override fun onComplete() {
                if(user != null) {
                    presenter.profileLoaded(user as User)
                }
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: User) {
                user = t
            }

            override fun onError(e: Throwable) {

            }


        })
    }


    override fun fetchRepo(username: String) {

        var tempList = mutableListOf<Repo>()

        userService.getRepo(username).
            subscribeOn(io.reactivex.schedulers.Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<List<Repo>>{
            override fun onComplete() {
                presenter.repoLoaded(tempList)
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<Repo>) {
                tempList.clear()
                tempList.addAll(t)
            }

            override fun onError(e: Throwable) {

            }

        })
    }

}