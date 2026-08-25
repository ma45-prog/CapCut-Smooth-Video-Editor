package com.infinix.videoeditor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.infinix.videoeditor.databinding.ActivityMainBinding
import com.infinix.videoeditor.ui.VideoEditorActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.newProjectBtn.setOnClickListener {
            startActivity(Intent(this, VideoEditorActivity::class.java))
        }

        binding.settingsBtn.setOnClickListener {
            // Navigate to settings
        }
    }
}