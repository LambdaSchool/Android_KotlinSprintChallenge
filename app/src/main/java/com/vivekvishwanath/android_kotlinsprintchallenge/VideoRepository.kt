package com.vivekvishwanath.android_kotlinsprintchallenge

import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class VideoRepository {
    private val dataJob = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + dataJob)
    private val hubbleApiDao = HubbleApiDao()

    fun getAllVideos(): MutableLiveData<List<Video>> {
        val videoLiveData = MutableLiveData<List<Video>>()
        val videoList = ArrayList<Video>()
        dataScope.launch {
            videoLiveData.postValue(videoList)
            val videosJsonArray = hubbleApiDao.getAllVideos()
            if (videosJsonArray != null) {
                for (i in 0 until videosJsonArray.size()) {
                    val videoListItem = Json.nonstrict.parse(VideoListItem.serializer(), videosJsonArray[i].toString())
                    val video = videoListItem.id?.let { hubbleApiDao.getSingleVideo(it) }
                    video?.let { videoList.add(it) }
                }
            }
        }
        return videoLiveData
    }

}