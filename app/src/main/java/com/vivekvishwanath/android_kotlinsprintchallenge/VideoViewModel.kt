package com.vivekvishwanath.android_kotlinsprintchallenge

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class VideoViewModel: ViewModel() {
    private var videoLiveData = MutableLiveData<List<Video>>()
    private val videoRepository = VideoRepository()

    fun getVideos(): MutableLiveData<List<Video>> {
        if (videoLiveData.value == null) {
            loadData()
        }
        return videoLiveData
    }

    private fun loadData() {
        videoLiveData = videoRepository.getAllVideos()
    }
}