package com.infinix.videoeditor.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.media3.exoplayer.ExoPlayer
import com.infinix.videoeditor.databinding.ActivityVideoEditorBinding
import com.infinix.videoeditor.viewmodel.VideoEditorViewModel

class VideoEditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoEditorBinding
    private lateinit var viewModel: VideoEditorViewModel
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(VideoEditorViewModel::class.java)
        initializePlayer()
        setupUI()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player
    }

    private fun setupUI() {
        binding.importVideoBtn.setOnClickListener {
            selectVideoFromStorage()
        }

        binding.trimBtn.setOnClickListener {
            // Navigate to trim video screen
        }

        binding.effectsBtn.setOnClickListener {
            // Navigate to effects screen
        }

        binding.textBtn.setOnClickListener {
            // Navigate to add text screen
        }

        binding.exportBtn.setOnClickListener {
            exportVideo()
        }
    }

    private fun selectVideoFromStorage() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "video/*"
        }
        startActivityForResult(intent, VIDEO_PICKER_REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            val videoUri: Uri? = data?.data
            videoUri?.let {
                viewModel.loadVideo(it)
                player?.setMediaItem(androidx.media3.common.MediaItem.fromUri(it))
                player?.prepare()
            }
        }
    }

    private fun exportVideo() {
        // Export video logic
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

    companion object {
        private const val VIDEO_PICKER_REQUEST_CODE = 100
    }
}