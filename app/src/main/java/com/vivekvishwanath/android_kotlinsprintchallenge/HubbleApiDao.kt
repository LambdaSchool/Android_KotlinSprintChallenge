package com.vivekvishwanath.android_kotlinsprintchallenge

import android.support.annotation.WorkerThread
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HubbleApiDao{
    private val BASE_URL = "http://hubblesite.org/api/v3/"
    private val SINGLE_VIDEO_URL = "http://hubblesite.org/api/v3/video/" + "%s"

    private var retrofit = Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).build()
    private var hubbleApiInterface = retrofit.create(HubbleApiInterface::class.java)
    private var gson = Gson()

    @WorkerThread
    fun getAllVideos(): JsonArray? {
        val call = hubbleApiInterface.getAllVideos()
        val videoList = ArrayList<Video>()
        return call.execute().body()?.asJsonArray
    }

    @WorkerThread
    fun getSingleVideo(id: Int): Video {
        val videoUrl = SINGLE_VIDEO_URL.format(id)
        val call = hubbleApiInterface.getSingleVideo(videoUrl)
        return Json.nonstrict.parse(Video.serializer(), call.execute().body().toString())
    }

}