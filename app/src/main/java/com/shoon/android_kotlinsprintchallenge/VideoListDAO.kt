package com.shoon.android_kotlinsprintchallenge

import androidx.annotation.WorkerThread
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject

object VideoListDAO{
    const val SPACETELESCOPE_URL = "http://hubblesite.org/api/v3/"
    const val VIDEO_ALL = "videos/all"
    const val VIDEO_URL = "video/"
    val jsnData="[{\"id\":1209,\"name\":\"How Do We Find Exoplanets?\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1209/thumb_low_STScI-J-WAOW_vignette_2a-420x236.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1210,\"name\":\"Eclipse/coronagraphy animation\",\"news_name\":\"b\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1210/thumb_low_STScI-J-CoronagraphyEclipse-420x236.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1205,\"name\":\"Tonight's Sky: June 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1205/thumb_low_STScI-H-Tonights-Sky_June_Constellations_2019-d1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1203,\"name\":\"Hubble Legacy Field – Zoom\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1203/thumb_low_STScI-H-v1917a-d-1280x720.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1204,\"name\":\"Tonight's Sky: May 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1204/thumb_low_STScI-H-Tonights-Sky_May_Constellations_2019-d1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1208,\"name\":\"Spectroscopy Animation of Southern Crab Nebula\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1208/thumb_low_STScI-H-v1915a-420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1206,\"name\":\"What Is a Galaxy?\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1206/thumb_low_STScI-J-v1927a-t420x236.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1207,\"name\":\"A Galaxy Grouping in 2D and 3D: Stephan's Quintet\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1207/thumb_low_stephans_quintet-example_frame-1920x1080.png\",\"collection\":\"science\",\"mission\":\"hubble\"},{\"id\":1201,\"name\":\"Tonight's Sky: April 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1201/thumb_low_STScI-H-Tonights-Sky_April_Constellations_2019-d1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1199,\"name\":\"Flyby of the Whirlpool Galaxy\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1199/thumb_low_m51-example_frame-1920x1080.png\",\"collection\":\"science\",\"mission\":\"hubble\"},{\"id\":1200,\"name\":\"NGC 5466 Visualization\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1200/thumb_low_STSCI-H-v1916a-t-400x400.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1198,\"name\":\"Massive Stars: Engines of Creation\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1198/thumb_low_STScI-J-v1913a-1920x1080.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1197,\"name\":\"Tonight's Sky: March 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1197/thumb_low_STScI-H-Tonights-Sky_March_Constellations_2019-d1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1195,\"name\":\"Shedding New Light on the Whirlpool Galaxy\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1195/thumb_low_m51_all_composites-3840x2160.jpg\",\"collection\":\"science\",\"mission\":\"hubble\"},{\"id\":1196,\"name\":\"Tonight's Sky: February 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1196/thumb_low_February_Constellations_Youtube-1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1193,\"name\":\"Zoom into the Triangulum Galaxy – Short Version\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1193/thumb_low_STScI-H-v1901a-M33-t420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1194,\"name\":\"Zoom into the Triangulum Galaxy – Long Version\",\"news_name\":\"b\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1194/thumb_low_STScI-H-v1901b-M33-t420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1192,\"name\":\"Tonight's Sky: January 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1192/thumb_low_January_Constellations_Youtube-1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1191,\"name\":\"Deep Field: The Impossible Magnitude of our Universe\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1191/thumb_low_STScI-H-Whitacre-Deep-Field-t-420x236.png\",\"collection\":\"science\",\"mission\":\"hubble\"},{\"id\":1190,\"name\":\"Webb Telescope Title Sequence\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1190/thumb_low_STScI-J-WebbTelescope-Title-Sequence-1280x720.png\",\"collection\":\"webb_non_news_assets\",\"mission\":\"james_webb\"},{\"id\":1189,\"name\":\"Are All Galaxies the Same?\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1189/thumb_low_STScI-J-v1859a-420x236.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1188,\"name\":\"Zoom and Pan of the Coma Cluster\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1188/thumb_low_STScI-H-v1844a-t420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1187,\"name\":\"Galaxy M100 Comparison of Hubble WFPC1 to WFC3\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1187/thumb_low_STSCI-H-v1848a-t-420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1042,\"name\":\"Tonight's Sky: December 2018\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1042/thumb_low_STScI-H-vTonightsSky-2018-12-t420x236.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1049,\"name\":\"Pillars of Creation: Visible and Infrared Views\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1049/thumb_low_STScI-H-v-multiwavelength-m16-eagle-nebula.jpg\",\"collection\":\"science\",\"mission\":\"hubble\"}]"
    val jsnDataVideoUrl="{\"name\":\"How Do We Find Exoplanets?\",\"short_description\":\"This video illustrates the different methods scientists use to find exoplanets, or planets orbiting distant stars.\",\"youtube_id\":\"fjd8I_cXUBE\",\"credits\":\"\\u003ca href=\\\"http://www.nasa.gov/\\\"\\u003eNASA\\u003c/a\\u003e, \\u003ca href=\\\"http://www.spacetelescope.org/\\\"\\u003eESA\\u003c/a\\u003e, and J. Olmsted (\\u003ca href=\\\"http://www.stsci.edu/\\\"\\u003eSTScI\\u003c/a\\u003e)\",\"news_name\":\"a\",\"mission\":\"james_webb\",\"collection\":\"news\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1209/low_STScI-J-WAOW_vignette_2a-420x236.png\",\"image_retina\":\"https://media.stsci.edu/uploads/video/image_attachment/1209/STScI-J-WAOW_vignette_2a-420x236.png\",\"html_5_video\":{\"video_url\":\"https://media.stsci.edu/uploads/video_file/video_attachment/5067/STScI-J-WAOW_vignette_2a-1280x720.mp4\",\"poster_url\":\"https://media.stsci.edu/uploads/video/preview_frame/1209/STScI-J-WAOW_vignette_2a-1280x720.png\"},\"video_files\":[{\"file_url\":\"https://media.stsci.edu/uploads/video_file/video_attachment/5066/STScI-J-WAOW_vignette_2a-1920x1080.mp4\",\"file_size\":19814025,\"width\":1920,\"height\":1080,\"frame_rate\":\"29.97\",\"format\":\"MPEG-4 (H.264)\"},{\"file_url\":\"https://media.stsci.edu/uploads/video_file/video_attachment/5067/STScI-J-WAOW_vignette_2a-1280x720.mp4\",\"file_size\":11516684,\"width\":1280,\"height\":720,\"frame_rate\":\"29.97\",\"format\":\"MPEG-4 (H.264)\"},{\"file_url\":\"https://media.stsci.edu/uploads/video_file/video_attachment/5068/STScI-J-WAOW_vignette_2a-640x360.mp4\",\"file_size\":5499651,\"width\":640,\"height\":360,\"frame_rate\":\"29.97\",\"format\":\"MPEG-4 (H.264)\"}]}"
    interface VideoListCallback{
        fun callback(list: List<VideosList>)
    }
/*
    fun getVideoList(callback: VideoListCallback, offset: Int = 0, limit: Int = 10){
        NetworkAdapter.httpGetRequest(
            "$SPACETELESCOPE_URL$VIDEO_ALL",
            object: NetworkAdapter.NetworkCallback{
                override fun returnResult(success: Boolean?, result: String) {
                    val videoList = Json.parse(VideosList.serializer(), result)
                    // callback.callback(videoList ?: listOf())
                }
            })
    }
*/
    @WorkerThread
    suspend fun getVideoListSuspend(): String {
        val result = NetworkAdapter.httpGetRequestCoroutine("$SPACETELESCOPE_URL$VIDEO_ALL")
        //val videoList = Json.parse(VideosList.serializer(), jsnData) as List<VideosList>
        return result
    }

/*    @WorkerThread
    suspend fun getVideoImage(videoList: VideosList?): Bitmap? {
        val result = NetworkAdapter.getBitmapFromURL(videoList?.image?: "")
        return if (result.first && result.second != null){
            result.second
        } else {
            null
        }
    }
*/
    @WorkerThread
    suspend fun getVideoURL(id: String): String? {
        val result = NetworkAdapter.httpGetRequestCoroutine("$SPACETELESCOPE_URL$VIDEO_URL$id")

        var vurl  = Json.nonstrict.parse(VideoURL.serializer(), result) //as JSONObject
        return vurl.video_files?.get(0)?.file_url
     //   var json=JSONObject(result)
      //  var jsna=json["video_files"] as JSONArray
        //var js2=jsna[0] as JSONObject
        //return js2["file_url"] as String
    }

}