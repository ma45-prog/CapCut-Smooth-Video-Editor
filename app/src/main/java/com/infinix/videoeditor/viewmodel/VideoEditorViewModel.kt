package com.infinix.videoeditor.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinix.videoeditor.data.VideoProject
import kotlinx.coroutines.launch

class VideoEditorViewModel : ViewModel() {

    private val _currentVideo = MutableLiveData<VideoProject?>()
    val currentVideo: LiveData<VideoProject?> = _currentVideo

    private val _isExporting = MutableLiveData(false)
    val isExporting: LiveData<Boolean> = _isExporting

    private val _exportProgress = MutableLiveData(0)
    val exportProgress: LiveData<Int> = _exportProgress

    fun loadVideo(uri: Uri) {
        viewModelScope.launch {
            val project = VideoProject(
                id = System.currentTimeMillis(),
                videoUri = uri,
                createdAt = System.currentTimeMillis()
            )
            _currentVideo.value = project
        }
    }

    fun trimVideo(startTime: Long, endTime: Long) {
        viewModelScope.launch {
            _currentVideo.value?.let { project ->
                val updated = project.copy(
                    trimStart = startTime,
                    trimEnd = endTime
                )
                _currentVideo.value = updated
            }
        }
    }

    fun addEffect(effectName: String, intensity: Float = 1.0f) {
        viewModelScope.launch {
            _currentVideo.value?.let { project ->
                val effects = project.effects.toMutableList()
                effects.add(effectName to intensity)
                val updated = project.copy(effects = effects)
                _currentVideo.value = updated
            }
        }
    }

    fun exportVideo() {
        viewModelScope.launch {
            _isExporting.value = true
            try {
                // Simulate export progress
                for (progress in 0..100 step 10) {
                    _exportProgress.value = progress
                    kotlinx.coroutines.delay(500)
                }
            } finally {
                _isExporting.value = false
            }
        }
    }
}