package com.himanshurawat.githubdeveloperapi.network

import com.himanshurawat.githubdeveloperapi.pojo.Users
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

import java.util.*

interface UserService {

    @GET("/users")
    fun getUsers(@Query("since")after: Long): Observable<List<Users>>



}