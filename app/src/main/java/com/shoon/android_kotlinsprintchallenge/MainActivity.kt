package com.shoon.android_kotlinsprintchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.shoon.android_kotlinsprintchallenge.dummy.DummyContent

class MainActivity : AppCompatActivity() , ItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {


        val args = Bundle()
        val strURL= "https://media.stsci.edu/uploads/video_file/video_attachment/3837/STScI-H-v0526a-640x480.mp4"
        val movieFragment =MovieFragment()
        args.putString(movieFragment.URLPATH, strURL)
        movieFragment.arguments=args
        manager = supportFragmentManager
        transaction = manager.beginTransaction()
        transaction.replace(R.id.frameMain, movieFragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    private lateinit var manager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = supportFragmentManager
        transaction = manager.beginTransaction()
        transaction.replace(R.id.frameMain, ItemFragment())
        transaction.addToBackStack(null)
        transaction.commit()


    }
}
