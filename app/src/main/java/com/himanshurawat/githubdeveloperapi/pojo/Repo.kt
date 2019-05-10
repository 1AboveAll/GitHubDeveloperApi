package com.himanshurawat.githubdeveloperapi.pojo

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("full_name")
    val fullName: String?,
    val description: String?,
    @SerializedName("stargazers_count")
    val stars: Int?,
    val language: String?
)