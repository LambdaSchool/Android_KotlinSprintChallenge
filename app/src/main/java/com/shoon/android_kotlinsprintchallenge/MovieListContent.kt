package com.shoon.android_kotlinsprintchallenge

import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonPrimitive
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import java.util.ArrayList
import java.util.HashMap
import com.shoon.android_kotlinsprintchallenge.MovieListContent.jsnData as jsnData1


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object MovieListContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<MovieItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, MovieItem> = HashMap()
    val jsnData="[{\"id\":1209,\"name\":\"How Do We Find Exoplanets?\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1209/thumb_low_STScI-J-WAOW_vignette_2a-420x236.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1210,\"name\":\"Eclipse/coronagraphy animation\",\"news_name\":\"b\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1210/thumb_low_STScI-J-CoronagraphyEclipse-420x236.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1205,\"name\":\"Tonight's Sky: June 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1205/thumb_low_STScI-H-Tonights-Sky_June_Constellations_2019-d1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1203,\"name\":\"Hubble Legacy Field – Zoom\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1203/thumb_low_STScI-H-v1917a-d-1280x720.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1204,\"name\":\"Tonight's Sky: May 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1204/thumb_low_STScI-H-Tonights-Sky_May_Constellations_2019-d1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1208,\"name\":\"Spectroscopy Animation of Southern Crab Nebula\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1208/thumb_low_STScI-H-v1915a-420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1206,\"name\":\"What Is a Galaxy?\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1206/thumb_low_STScI-J-v1927a-t420x236.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1207,\"name\":\"A Galaxy Grouping in 2D and 3D: Stephan's Quintet\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1207/thumb_low_stephans_quintet-example_frame-1920x1080.png\",\"collection\":\"science\",\"mission\":\"hubble\"},{\"id\":1201,\"name\":\"Tonight's Sky: April 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1201/thumb_low_STScI-H-Tonights-Sky_April_Constellations_2019-d1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1199,\"name\":\"Flyby of the Whirlpool Galaxy\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1199/thumb_low_m51-example_frame-1920x1080.png\",\"collection\":\"science\",\"mission\":\"hubble\"},{\"id\":1200,\"name\":\"NGC 5466 Visualization\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1200/thumb_low_STSCI-H-v1916a-t-400x400.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1198,\"name\":\"Massive Stars: Engines of Creation\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1198/thumb_low_STScI-J-v1913a-1920x1080.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1197,\"name\":\"Tonight's Sky: March 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1197/thumb_low_STScI-H-Tonights-Sky_March_Constellations_2019-d1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1195,\"name\":\"Shedding New Light on the Whirlpool Galaxy\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1195/thumb_low_m51_all_composites-3840x2160.jpg\",\"collection\":\"science\",\"mission\":\"hubble\"},{\"id\":1196,\"name\":\"Tonight's Sky: February 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1196/thumb_low_February_Constellations_Youtube-1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1193,\"name\":\"Zoom into the Triangulum Galaxy – Short Version\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1193/thumb_low_STScI-H-v1901a-M33-t420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1194,\"name\":\"Zoom into the Triangulum Galaxy – Long Version\",\"news_name\":\"b\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1194/thumb_low_STScI-H-v1901b-M33-t420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1192,\"name\":\"Tonight's Sky: January 2019\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1192/thumb_low_January_Constellations_Youtube-1280x720.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1191,\"name\":\"Deep Field: The Impossible Magnitude of our Universe\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1191/thumb_low_STScI-H-Whitacre-Deep-Field-t-420x236.png\",\"collection\":\"science\",\"mission\":\"hubble\"},{\"id\":1190,\"name\":\"Webb Telescope Title Sequence\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1190/thumb_low_STScI-J-WebbTelescope-Title-Sequence-1280x720.png\",\"collection\":\"webb_non_news_assets\",\"mission\":\"james_webb\"},{\"id\":1189,\"name\":\"Are All Galaxies the Same?\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1189/thumb_low_STScI-J-v1859a-420x236.png\",\"collection\":\"news\",\"mission\":\"james_webb\"},{\"id\":1188,\"name\":\"Zoom and Pan of the Coma Cluster\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1188/thumb_low_STScI-H-v1844a-t420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1187,\"name\":\"Galaxy M100 Comparison of Hubble WFPC1 to WFC3\",\"news_name\":\"a\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1187/thumb_low_STSCI-H-v1848a-t-420x236.png\",\"collection\":\"news\",\"mission\":\"hubble\"},{\"id\":1042,\"name\":\"Tonight's Sky: December 2018\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1042/thumb_low_STScI-H-vTonightsSky-2018-12-t420x236.png\",\"collection\":\"tonights_sky\",\"mission\":\"hubble\"},{\"id\":1049,\"name\":\"Pillars of Creation: Visible and Infrared Views\",\"image\":\"https://media.stsci.edu/uploads/video/image_attachment/1049/thumb_low_STScI-H-v-multiwavelength-m16-eagle-nebula.jpg\",\"collection\":\"science\",\"mission\":\"hubble\"}]"

    private val COUNT = 25

    init {
        var json= JSONArray(jsnData)

        // Add some sample items.
        for (i in 0..1) {

           var jsono:JSONObject = json[0] as JSONObject

            addItem(
                MovieItem(
                    jsono["id"].toString(),
                    jsono["name"].toString(),
                    jsono["news_name"].toString()
                )


            )
        }
    }

    private fun addItem(item: MovieItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }



    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class MovieItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }




}
