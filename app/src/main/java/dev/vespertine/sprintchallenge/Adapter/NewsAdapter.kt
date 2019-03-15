package dev.vespertine.sprintchallenge.Adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dev.vespertine.sprintchallenge.Dao.HubbleDAO
import dev.vespertine.sprintchallenge.Model.HubbleNewsPage
import dev.vespertine.sprintchallenge.R
import kotlinx.android.synthetic.main.news_list_element_layout.*
import kotlinx.coroutines.*

class HubbleAdapter(val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val newList = mutableListOf<HubbleNewsPage>()

    private val adapter = this
    private val dataJob = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

    class HubbleNewsPageViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val newsIDtextview: TextView = view.findViewById(R.id.rview_news_id)
        val newsNametextview: TextView = view.findViewById(R.id.rview_news_name)
    }

    init {
        getNewsPage()
    }

    private fun getNewsPage(){
        dataScope.launch {
            val newsList = HubbleDAO.getAllNewsStories()
            newList.addAll(newsList)
            withContext(Dispatchers.Main) {
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}