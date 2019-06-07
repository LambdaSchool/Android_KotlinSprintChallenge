package com.shoon.android_kotlinsprintchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.shoon.android_kotlinsprintchallenge.dummy.DummyContent

class MainActivity : AppCompatActivity() , ItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {


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
