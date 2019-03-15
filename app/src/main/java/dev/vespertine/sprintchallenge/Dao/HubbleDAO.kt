package dev.vespertine.sprintchallenge.Dao

object HubbleDAO {
    //will grab 23 news stories.
    const val HUBBLE_NEWS_PAGE_URL = "http://hubblesite.org/api/v3/news?page="

    // from link above - news_id will be used to pull specific stories. added to end
    const val HUBBLE_NEWS_SPECIFIC = "http://hubblesite.org/api/v3/news_release/"

    //add video id to the end
    const val HUBBLE_VIDEO_SPECIFIC = "http://hubblesite.org/api/v3/video/"

    //add image id to the end. Might not need
    const val HUBBLE_IMAGE_SPECIFIC = "http://hubblesite.org/api/v3/image/"




}