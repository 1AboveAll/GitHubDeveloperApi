package com.himanshurawat.githubdeveloperapi.network

import com.himanshurawat.githubdeveloperapi.pojo.Repo
import com.himanshurawat.githubdeveloperapi.pojo.User
import com.himanshurawat.githubdeveloperapi.pojo.Users
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import java.util.*

interface NetworkService {

    @GET("/users")
    fun getUsers(@Query("since")after: Long): Observable<List<Users>>

    @GET("/users/{username}")
    fun getUser(@Path("username")username: String): Observable<User>

    @GET("/users/{username}/repos")
    fun getRepo(@Path("username")username: String,@Query("sort")sort: String = "updated"): Observable<List<Repo>>

}