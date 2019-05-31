package com.lambdaschool.android_kotlinsprintchallenge

import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

object HubbleVideoDao {

    @WorkerThread
    fun retrieveHubbleVideoDataById(videoId: String): VideoModel? {
        var videoModel: VideoModel? = null

        val result = NetworkAdapter.httpGetRequest("http://hubblesite.org/api/v3/video/$videoId")

        if (result.first) {
            result.let {
                videoModel = Json.nonstrict.parse(VideoModel.serializer(), it.second)
            }
        }

        return videoModel
    }
}

@UiThread
private suspend fun dataWait() {
    delay(1000)
}
