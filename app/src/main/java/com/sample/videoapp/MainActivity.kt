package com.sample.videoapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(AddVideoFragment())
    }
    private fun openFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        if(addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            if(intent!=null && intent.data!=null){
                 val videoUri: Uri = intent.data!!
                openFragment(PlayVideoFragment.newInstance(videoUri),true)
            }
        }else if(requestCode == REQUEST_VIDEO_PICK && resultCode == RESULT_OK){
            if(intent!=null && intent.data!=null){
                val videoUri: Uri = intent.data!!
                openFragment(PlayVideoFragment.newInstance(videoUri),true)

            }
        }
    }
}