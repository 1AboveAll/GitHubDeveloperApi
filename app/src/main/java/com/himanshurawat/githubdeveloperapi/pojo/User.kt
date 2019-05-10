package com.himanshurawat.githubdeveloperapi.pojo

import com.google.gson.annotations.SerializedName

data class User(
    val login: String?,
    val id: Long?,
    @SerializedName("avatar_url")
    val profilePictureUrl: String?,
    @SerializedName("followers_url")
    val followersUrl: String?,
    @SerializedName("following_url")
    val followingUrl: String?,
    @SerializedName("repos_url")
    val repoUrl: String?,
    val name: String?,
    val bio: String?,
    @SerializedName("public_repos")
    val publicRepos: Int?,
    @SerializedName("public_gists")
    val publicGists: Int?,
    val location: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    @SerializedName("created_at")
    val createdAt: String
)