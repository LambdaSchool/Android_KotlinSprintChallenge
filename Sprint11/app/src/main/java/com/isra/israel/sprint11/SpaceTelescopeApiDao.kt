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
        fun getVideos(page: Int?) : List<VideoResult> {

            var out: List<VideoResult> = listOf()
            buildString {
                append(BASE_URL)
                append(VIDEOS)
                if (page != null) {
                    append("?page=$page")
                }

                NetworkAdapter.httpRequest(toString(), NetworkAdapter.GET, null, null) { code, body ->
                    if (NetworkAdapter.isSuccessful(code) && body != null) {
                        out = Json.nonstrict.parse(VideoResult.serializer().list, body)
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

        @WorkerThread
        fun getVideoFile(videoUrl: String) : String? {

            var out: String? = null
            NetworkAdapter.httpsRequest(videoUrl, NetworkAdapter.GET, null, null) { code, body ->
                if (NetworkAdapter.isSuccessful(code) && body != null) {
                    out = body
                }
            }

            return out
        }

    }
}