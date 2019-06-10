package com.shoon.android_kotlinsprintchallenge

import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction



class MovieFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    private lateinit var btnOpen: Button
    private lateinit var btnplay: Button
    private lateinit var vv: VideoView
    private var uri: Uri? = null
    private lateinit var textDebug: TextView
    private lateinit var seekbar:SeekBar

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
        seekbar=this.view!!.findViewById(R.id.seekbar)
        seekbar.setOnSeekBarChangeListener(this)
        btnOpen = this.view!!.findViewById(R.id.btnOpen) as Button
        vv = this.view!!.findViewById(R.id.vv) as VideoView
        textDebug= this.view!!.findViewById(R.id.text_debug)
        val uriPath =this.arguments!!.get(URLPATH)as String

        uri = Uri.parse(uriPath)

        btnplay = this.view!!.findViewById(R.id.btnplay) as Button
        btnplay!!.setOnClickListener {
            if(vv.isPlaying){
                vv!!.pause()
                btnplay!!.setText("PLAY")

     //           updateSeekbar.run()
            }else{

                btnplay!!.setText("PAUSE")
                vv!!.start()
     //           updateSeekbar.run()
            }

        }


        seekbar=this.view!!.findViewById(R.id.seekbar)
        seekbar?.setOnSeekBarChangeListener(this)

        vv!!.setVideoURI(uri)
        vv!!.requestFocus()
        vv!!.start()
        vv.setOnPreparedListener {it->
            while (vv.isPlaying){
                this.activity?.runOnUiThread(Runnable {
                    seekbar.progress=vv.currentPosition
                    seekbar.secondaryProgress=vv.duration/vv.bufferPercentage
                })

                try {
                    Thread.sleep((vv.duration / vv.width).toLong())
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
        }

        val videoProgressListenerRunnable: Runnable = Runnable {
            while (vv.isPlaying){
                this.activity?.runOnUiThread(Runnable {
                    seekbar.progress=vv.currentPosition
                    seekbar.secondaryProgress=vv.duration/vv.bufferPercentage
                })

                try {
                    Thread.sleep((vv.duration / vv.width).toLong())
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
        }



        vv!!.setOnCompletionListener {
            seekbar.setProgress(0);

            vv!!.start()
        }


        btnOpen!!.setOnClickListener {
            manager = this.activity!!.supportFragmentManager
            transaction = manager.beginTransaction()
            transaction.replace(R.id.frameMain, ItemFragment())
            transaction.addToBackStack(null)
            transaction.commit()

        }

        vv!!.setOnPreparedListener { /*seekbar.visibility = View.GONE */}


    }

    private val updateSeekbar = object : Runnable {
        override fun run() {
            val seekbarUpdateHandler = Handler()
            seekbar.setProgress(vv.currentPosition)
            seekbarUpdateHandler.postDelayed(this, 50)
        }
    }
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser){

            vv.seekTo(vv.duration*progress/100)
            textDebug.setText(progress.toString()+","+vv.duration.toString()+","+vv.currentPosition.toString())
        }

        seekBar!!.secondaryProgress = this.vv.duration*vv.bufferPercentage / 100
        textDebug.append("\n"+seekbar.secondaryProgress.toString()+","+vv.bufferPercentage.toString())

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {


    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

}
