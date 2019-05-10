package com.himanshurawat.githubdeveloperapi.ui.main

import android.content.Context
import com.himanshurawat.githubdeveloperapi.network.NetworkClient
import com.himanshurawat.githubdeveloperapi.network.UserService
import com.himanshurawat.githubdeveloperapi.pojo.Users
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import rx.Subscriber
import rx.plugins.RxJavaSchedulersHook
import rx.schedulers.Schedulers

class MainActivityModel(context: Context,val presenter: MainActivityContract.Presenter): MainActivityContract.Model {

    private var retrofit: Retrofit = NetworkClient.getRetrofit(context)
    private var userService = retrofit.create(UserService::class.java)

    override fun fetch(sequenceNumber: Long) {

        val myList = mutableListOf<Users>()

        userService.getUsers(sequenceNumber).
            subscribeOn(io.reactivex.schedulers.Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<List<Users>>{
            override fun onComplete() {
                presenter.dataLoaded(myList)
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<Users>) {
                myList.clear()
                myList.addAll(t)
            }

            override fun onError(e: Throwable) {

            }

        })
    }
}