package com.vivekvishwanath.android_kotlinsprintchallenge

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface HubbleApiInterface {
    @GET("videos")
    fun getAllVideos(): Call<JsonElement>

    @GET()
    fun getSingleVideo(@Url url: String): Call<JsonElement>
}