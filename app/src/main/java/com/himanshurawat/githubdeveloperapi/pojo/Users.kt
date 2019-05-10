package com.himanshurawat.githubdeveloperapi.pojo

import com.google.gson.annotations.SerializedName

data class Users(
    val login: String?,
    val id: Long?,
    @SerializedName("avatar_url")
    val profilePictureUrl: String?,
    @SerializedName("followers_url")
    val followersUrl: String?,
    @SerializedName("following_url")
    val followingUrl: String?,
    @SerializedName("repos_url")
    val repoUrl: String?
)