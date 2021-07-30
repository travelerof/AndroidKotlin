package com.hyg.video.core

import android.net.Uri

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
class VideoSource private constructor(private val uri: Uri) {

    companion object {
        @JvmStatic
        fun formUri(url: String): VideoSource = formUri(Uri.parse(url))

        @JvmStatic
        fun formUri(uri: Uri): VideoSource = VideoSource(uri)
    }

    fun uri(): Uri = uri
}