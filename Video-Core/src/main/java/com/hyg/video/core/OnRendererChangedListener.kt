package com.hyg.video.core

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc
 */
interface OnRendererChangedListener {

    fun onVideoSizeChanged(width: Int, height: Int)

    fun onSurfaceSizeChanged(width: Int,height: Int)
}