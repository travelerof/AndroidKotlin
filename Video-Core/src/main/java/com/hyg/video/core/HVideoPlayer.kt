package com.hyg.video.core

import android.content.Context

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
abstract class HVideoPlayer(private val context: Context) : HPlayer {

    private val videoSources = mutableListOf<VideoSource>()
    private var playStatus: Int = 0

    /**
     * 是否准备完成
     */
    protected var prepareComplete: Boolean = false

    /**
     * 当前视频播放位置
     */
    private var index: Int = 0

    private var playerListener: OnPlayerListener? = null
    protected var rendererListener: OnRendererChangedListener? = null

    protected fun getContext() = context

    override fun setPlayIndex(index: Int) {
        this.index = if (index >= videoSources.size) {
            videoSources.size
        } else if (index < 0) {
            0
        } else {
            index
        }
    }

    override fun getPlayIndex(): Int = index

    override fun getVideoSources(): MutableList<VideoSource> = videoSources

    override fun addVideoSource(element: VideoSource) {
        if (videoSources.isEmpty()) {
            playerListener?.onEvent(PlayStatus.FIRST_ADD_VIDEO_SOURCE)
        }
        videoSources.add(element)
    }

    override fun addVideoSources(elements: ArrayList<VideoSource>) {
        if (videoSources.isEmpty()) {
            playerListener?.onEvent(PlayStatus.FIRST_ADD_VIDEO_SOURCE)
        }
        videoSources.addAll(elements)
    }

    override fun getCurrentVideoSource(): VideoSource = videoSources.get(index)

    override fun setOnPlayerListener(onPlayerListener: OnPlayerListener) {
        this.playerListener = onPlayerListener
    }

    override fun setOnRendererChangedListener(onRendererChangedListener: OnRendererChangedListener) {
        rendererListener = onRendererChangedListener
    }

    override fun removeOnPlayerListener() {
        playerListener = null
    }

    override fun removeOnRendererChangedListener() {
        rendererListener = null
    }

    override fun removeAllListener() {
        removeOnPlayerListener()
        removeOnRendererChangedListener()
    }

    override fun release() {
        videoSources.clear()
        index = 0
        playerListener = null
    }

    override fun getStatus(): Int = playStatus

    protected fun setPlayCode(@PlayStatus code: Int) {
        playStatus = code
        playerListener?.onEvent(code)
    }

    protected fun setError(@PlayError code: Int, error: String, throwable: Throwable?) {
        playerListener?.onError(code, error, throwable)
    }
}