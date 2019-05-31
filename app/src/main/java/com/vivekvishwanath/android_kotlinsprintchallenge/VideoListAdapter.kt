package com.vivekvishwanath.android_kotlinsprintchallenge

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_list_item.view.*

class VideoListAdapter(videoList: List<Video>, context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = videoList
    private val context = context

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoNameView: TextView = view.video_name
        val videoItemParent: LinearLayout = view.video_item_parent
        val videoImage: ImageView = view.video_image

        fun bindModel(video: Video) {
            videoNameView.text = video.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false) as View
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ViewHolder
        holder.bindModel(data[position])
        Picasso.get().load(data[position].image).into(itemHolder.videoImage)
        holder.videoItemParent.setOnClickListener{
            val intent = Intent(context, ViewActivity::class.java)
            intent.putExtra("video", data[position])
            context.startActivity(intent)
        }
    }
}