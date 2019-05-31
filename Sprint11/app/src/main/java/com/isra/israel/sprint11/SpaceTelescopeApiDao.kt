package com.isra.israel.sprint11

import android.support.annotation.WorkerThread
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class SpaceTelescopeApiDao {

    companion object {
        const val BASE_URL = "http://hubblesite.org/api/v3/"
        const val VIDEOS = "videos/"
        const val VIDEO = "video/"

        @WorkerThread
        fun getVideos(page: Int?) : MutableList<VideoResult> {

            var out: MutableList<VideoResult> = ArrayList<VideoResult>()
            buildString {
                append(BASE_URL)
                append(VIDEOS)
                if (page != null) {
                    append("?page=$page")
                }

                NetworkAdapter.httpRequest(toString(), NetworkAdapter.GET, null, null) { code, body ->
                    if (NetworkAdapter.isSuccessful(code) && body != null) {
                        //out = Json.nonstrict.parse(VideoResult.serializer().list(), body)
                        out = ArrayList<VideoResult>()
                    }
                }
            }

            return out
        }

        @WorkerThread
        suspend fun getVideo(id: Int) : Video? {

            var video: Video? = null
            buildString {
                append(BASE_URL)
                append(VIDEO)
                append(id)

                NetworkAdapter.httpRequest(toString(), NetworkAdapter.GET, null, null) { code, body ->
                    if (NetworkAdapter.isSuccessful(code) && body != null) {
                        video = Json.nonstrict.parse(Video.serializer(), body)
                    }
                }
            }

            return video
        }

    }
}