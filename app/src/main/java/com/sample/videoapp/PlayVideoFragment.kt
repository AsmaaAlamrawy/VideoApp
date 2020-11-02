package com.sample.videoapp

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_play_video.*

private const val VIDEO_URI = "videoURI"

class PlayVideoFragment : Fragment() {
    private var videoURI: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoURI = it.getParcelable(VIDEO_URI)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_play_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViedoView()
    }

    private fun setupViedoView() {
        val mediaController = MediaController(activity!!)
        mediaController.setAnchorView(video_view)
        video_view.setMediaController(mediaController)
        if(videoURI!=null){
            video_view.setVideoURI(videoURI)
        }
        play_btn.setOnClickListener { video_view.start() }
        pause_btn.setOnClickListener { video_view.pause() }
        handleVideoPlayTime()
    }

    private fun handleVideoPlayTime() {
        var runnable: Runnable
        var handler : Handler = Handler()
        runnable =   object : Runnable{
             override fun run() {
                 var currentPostion : Int = video_view.getCurrentPosition()
                 //limit play time to 10 seconds
                if(currentPostion >= 10 * 1000) {
                    video_view.pause()
                    video_view.seekTo(0)
                }
                handler.postDelayed(this, 250);
            }
        }
        handler.post(runnable);
    }

    companion object {
        @JvmStatic
        fun newInstance(videoURI: Uri) =
            PlayVideoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(VIDEO_URI, videoURI)
                }
            }
    }
}