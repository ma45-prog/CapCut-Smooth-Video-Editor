package com.infinix.videoeditor.data

import android.net.Uri

data class VideoProject(
    val id: Long,
    val videoUri: Uri,
    val createdAt: Long,
    val trimStart: Long = 0,
    val trimEnd: Long = 0,
    val effects: List<Pair<String, Float>> = emptyList(),
    val textOverlays: List<TextOverlay> = emptyList(),
    val transitions: List<Transition> = emptyList(),
    val audioTracks: List<AudioTrack> = emptyList()
)

data class TextOverlay(
    val id: String,
    val text: String,
    val startTime: Long,
    val endTime: Long,
    val x: Float,
    val y: Float,
    val fontSize: Float,
    val color: Int
)

data class Transition(
    val id: String,
    val type: String,
    val duration: Long,
    val startTime: Long
)

data class AudioTrack(
    val id: String,
    val uri: Uri,
    val startTime: Long,
    val volume: Float
)