package com.shoon.android_kotlinsprintchallenge

import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MovieFragment : Fragment() {
    private var btnonce: Button? = null
    private var btnOpen: Button? = null
    private var btnplay: Button? = null
    private var vv: VideoView? = null
    private var mediacontroller: MediaController? = null
    private var uri: Uri? = null
    private var progressBar: ProgressBar? = null
    val URLPATH="URLPATH"
    private lateinit var manager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        progressBar = this.view!!.findViewById(R.id.progrss) as ProgressBar

        btnOpen = this.view!!.findViewById(R.id.btnOpen) as Button


        vv = this.view!!.findViewById(R.id.vv) as VideoView

        mediacontroller = MediaController(this.activity)
        mediacontroller!!.setAnchorView(vv)

        val uriPath =this.arguments!!.get(URLPATH)as String

        uri = Uri.parse(uriPath)

        progressBar!!.visibility = View.VISIBLE
        vv!!.setMediaController(mediacontroller)
        vv!!.setVideoURI(uri)
        vv!!.requestFocus()
        vv!!.start()


        vv!!.setOnCompletionListener {
            vv!!.start()
        }


        btnOpen!!.setOnClickListener {
            manager = this.activity!!.supportFragmentManager
            transaction = manager.beginTransaction()
            transaction.replace(R.id.frameMain, ItemFragment())
            transaction.addToBackStack(null)
            transaction.commit()

        }

        vv!!.setOnPreparedListener { progressBar!!.visibility = View.GONE }




    }

}
