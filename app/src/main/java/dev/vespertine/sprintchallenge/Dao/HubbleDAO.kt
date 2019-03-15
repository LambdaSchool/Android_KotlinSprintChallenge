package dev.vespertine.sprintchallenge.Dao

import android.provider.MediaStore
import android.support.annotation.WorkerThread
import dev.vespertine.sprintchallenge.Model.HubbleNewsPage
import dev.vespertine.sprintchallenge.Networking.NetworkAdapter
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

object HubbleDAO {
    //will grab 23 news stories.
    const val HUBBLE_NEWS_PAGE_URL = "http://hubblesite.org/api/v3/news?page="

    // from link above - news_id will be used to pull specific stories. added to end
    const val HUBBLE_NEWS_SPECIFIC = "http://hubblesite.org/api/v3/news_release/"

    //add video id to the end
    const val HUBBLE_VIDEO_SPECIFIC = "http://hubblesite.org/api/v3/video/"

    //add image id to the end. Might not need
    const val HUBBLE_IMAGE_SPECIFIC = "http://hubblesite.org/api/v3/image/"

    @WorkerThread
    suspend fun getAllNewsStories(): List<HubbleNewsPage> {
        val (success, result) = NetworkAdapter.httpGetRequest(HUBBLE_NEWS_PAGE_URL)
        var newsList: List<HubbleNewsPage>? = null
        if(success) {
            newsList = Json.parse(HubbleNewsPage.serializer().list, result)
        }
        return newsList?: listOf()
    }






}