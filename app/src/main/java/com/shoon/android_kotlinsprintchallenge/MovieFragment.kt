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


class MovieFragment : Fragment() {
    private var btnonce: Button? = null
    private var btnOpen: Button? = null
    private var btnplay: Button? = null
    private var vv: VideoView? = null
    private var mediacontroller: MediaController? = null
    private var uri: Uri? = null
    private var progressBar: ProgressBar? = null
    val URLPATH="URLPATH"

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
        btnonce = this.view!!.findViewById(R.id.btnonce) as Button
        btnOpen = this.view!!.findViewById(R.id.btnOpen) as Button

        btnplay = this.view!!.findViewById(R.id.btnplay) as Button
        vv = this.view!!.findViewById(R.id.vv) as VideoView

        mediacontroller = MediaController(this.activity)
        mediacontroller!!.setAnchorView(vv)

        val uriPath =this.arguments!!.get(URLPATH)as String

        uri = Uri.parse(uriPath)



        vv!!.setOnCompletionListener {
            vv!!.start()
        }

        btnplay!!.setOnClickListener {
            if(btnplay!!.text=="PLAY"){
                vv!!.start()
                btnplay!!.setText("PAUSE")
            }else{
                btnplay!!.setText("PLAY")
                vv!!.pause()

            }

        }

        btnonce!!.setOnClickListener {
            progressBar!!.visibility = View.VISIBLE
            vv!!.setMediaController(mediacontroller)
            vv!!.setVideoURI(uri)
            vv!!.requestFocus()
            vv!!.start()
        }

        btnOpen!!.setOnClickListener {

        }

        vv!!.setOnPreparedListener { progressBar!!.visibility = View.GONE }




    }

}
