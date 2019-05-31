package com.isra.israel.sprint11

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_video_result.view.*
import kotlinx.coroutines.*

class VideoResultListAdapter : RecyclerView.Adapter<VideoResultListAdapter.ViewHolder>() {
    private var videoResultList: MutableList<VideoResult> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_video_result, p0, false))
    }

    override fun getItemCount(): Int {
        return videoResultList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindVideoResult(videoResultList[p1])
    }

    fun setVideoList(videoResultList: MutableList<VideoResult>) {
        this.videoResultList = videoResultList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val videoJob = Job()
        private val videoScope = CoroutineScope(Dispatchers.IO + videoJob)

        fun bindVideoResult(videoResult: VideoResult) {
            itemView.i_video_result_name.text = videoResult.name

            itemView.setOnClickListener {
                val videoId = videoResult.id ?: return@setOnClickListener
                videoScope.launch {
                    if (videoResult.id != null) {
                        val video = SpaceTelescopeApiDao.getVideo(videoId) ?: return@launch

                        withContext(Dispatchers.Main) {
                            val intent = Intent(itemView.context, VideoPlayerActivity::class.java)
                            intent.putExtra("video", video)
                            itemView.context.startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}