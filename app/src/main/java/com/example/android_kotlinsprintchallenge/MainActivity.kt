package com.example.android_kotlinsprintchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.VideoView
import com.example.android_kotlinsprintchallenge.model.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainActivity : AppCompatActivity() {

    val dataJob = Job()
    val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNext = findViewById<Button>(R.id.next_button)
        val buttonPause = findViewById<Button>(R.id.pause_button)
        val buttonPrevious = findViewById<Button>(R.id.previous_button)
        val seekbar = findViewById<SeekBar>(R.id.seekBar)
        val videoView = findViewById<VideoView>(R.id.player_video)



       buttonPause.setOnClickListener{
            val isPlaying = videoView.isPlaying
           if(isPlaying){
               videoView.pause()}
           else{
               videoView.start()
           }

        //   buttonNext.text = "skip"
           //buttonNext.setOnClickListener {
              // val isContinuation
           //}

         //  buttonPrevious.text = "Back"


              /** if (mediaPlayer == null) {
                   mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.im_yours);
                   mediaPlayer.start();
               } else if(!mediaPlayer.isPlaying()) {
                   mediaPlayer.seekTo(pausePosition);
                   mediaPlayer.start();

               }
               break;

               case R.id.pause_button:
               if(mediaPlayer!=null) {
                   mediaPlayer.pause();
                   pausePosition=mediaPlayer.getCurrentPosition();

               }
               break;

               case R.id.stop_button:
               if (mediaPlayer == null) {
                   mediaPlayer.stop();
                   mediaPlayer=null;
               }
               break; **/

       }

    }
}
