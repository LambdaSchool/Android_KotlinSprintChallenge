package com.shoon.android_kotlinsprintchallenge

import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.*


class MovieFragment : Fragment() {
    private var btnonce: Button? = null
    private var btnOpen: Button? = null
    private var btnplay: Button? = null
    private var vv: VideoView? = null
    private var mediacontroller: MediaController? = null
    private var uri: Uri? = null
    private var progressBar: ProgressBar? = null
    private var seekbar:SeekBar?=null

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

        btnplay = this.view!!.findViewById(R.id.btnplay) as Button
        btnplay!!.setOnClickListener {
            if(btnplay!!.text=="PLAY"){
                vv!!.start()
                btnplay!!.setText("PAUSE")
            }else{
                btnplay!!.setText("PLAY")
                vv!!.pause()

            }

        }
        seekbar=this.view!!.findViewById(R.id.seekbar)
        seekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var i= vv!!.currentPosition

                // Write code to perform some action when progress is changed.
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.
             }
        })



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
