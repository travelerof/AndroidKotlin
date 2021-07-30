package com.hyg.video.core

import android.graphics.Bitmap
import android.view.Surface

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
interface HPlayer {

    /**
     * 初始化内核
     */
    fun initPlayer()

    /**
     * 视频资源准备
     */
    fun prepare()

    /**
     * 播放
     */
    fun play()

    /**
     * 暂停
     */
    fun pause()

    /**
     * 停止播放
     */
    fun stop()

    /**
     * 是否正在播放
     */
    fun isPlaying(): Boolean

    /**
     * 重置播放器
     */
    fun reset()

    /**
     * 设置播放位置
     *
     * @param currentPosition Long
     */
    fun seekTo(currentPosition: Long)

    /**
     * 释放资源
     */
    fun release()

    /**
     * 获取当前播放位置
     *
     * @return Long
     */
    fun getCurrentPosition(): Long

    /**
     * 视频总长
     *
     * @return Long
     */
    fun getDuration(): Long

    /**
     * 设置播放速度
     * @param speed Float
     */
    fun setSpeed(speed: Float)

    /**
     * 获取播放速度
     *
     * @return Float
     */
    fun getSpeed(): Float

    /**
     * 设置音量
     *
     * @param volume Float 0~1
     */
    fun setVolume(volume: Float)

    /**
     * 获取当前音量
     * @return Float
     */
    fun getVolume(): Float

    /**
     * 获取当前缓冲位置
     * @return Long
     */
    fun getBufferedPosition(): Long

    /**
     * 设置渲染器
     *
     * @param surface Surface?
     */
    fun setSurface(surface: Surface?)

    /**
     * 获取时间对应帧
     *
     * @param currentPosition Long
     * @return Bitmap
     */
    fun getFrameAtTime(currentPosition: Long): Bitmap?

    /**
     * 设置单个视频资源
     *
     * @param element VideoSource
     */
    fun addVideoSource(element: VideoSource)

    /**
     * 设置多个视频资源
     * @param elements ArrayList<VideoSource>
     */
    fun addVideoSources(elements: ArrayList<VideoSource>)

    /**
     * 设置当前正在播放的视频index
     *
     * @param index Int
     */
    fun setPlayIndex(index: Int)

    /**
     * 获取当前正在播放的视频Index
     * @return Int
     */
    fun getPlayIndex(): Int

    /**
     * 获取当前视频列表
     * @return ArrayList<VideoSource>
     */
    fun getVideoSources(): MutableList<VideoSource>

    /**
     * 获取当前正在播放的视频资源
     *
     * @return VideoSource
     */
    fun getCurrentVideoSource(): VideoSource

    /**
     * 视频播放监听
     *
     * @param onPlayerListener OnPlayerListener
     */
    fun setOnPlayerListener(onPlayerListener: OnPlayerListener)

    /**
     * 视频尺寸或者渲染器变化时
     *
     * @param onRendererChangedListener OnRendererChangedListener
     */
    fun setOnRendererChangedListener(onRendererChangedListener: OnRendererChangedListener)
    /**
     * 移除监听
     */
    fun removeOnPlayerListener()

    fun removeOnRendererChangedListener()

    fun removeAllListener()
    /**
     * 当前状态
     *
     * @return Int
     */
    fun getStatus(): Int
}