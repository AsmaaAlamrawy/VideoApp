package com.sample.videoapp

import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_add_video.*

const val REQUEST_VIDEO_CAPTURE = 1
const val REQUEST_VIDEO_PICK = 2

class AddVideoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addVideo()
    }

    private fun addVideo(){
        add_video_btn.setOnClickListener {
            DialogMaker.makeDialog(activity, getString(R.string.capture_or_pick) , getString(R.string.capture),
                { _: DialogInterface, _: Int ->
                    dispatchTakeVideoIntent()
                },
                getString(R.string.pick), { _: DialogInterface, _: Int -> dispatchPickVideoIntent()  }).show()

        }


    }
    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10)
            takeVideoIntent.resolveActivity(activity!!.packageManager)?.also {
                activity!!.startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }

    private fun dispatchPickVideoIntent() {
        Intent(ACTION_PICK,MediaStore.Video.Media.EXTERNAL_CONTENT_URI).also { pickVideoIntent ->
            pickVideoIntent.resolveActivity(activity!!.packageManager)?.also {
                activity!!.startActivityForResult(pickVideoIntent, REQUEST_VIDEO_PICK)
            }
        }
    }


}