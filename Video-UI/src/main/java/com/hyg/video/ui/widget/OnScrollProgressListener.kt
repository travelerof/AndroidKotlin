package com.hyg.video.ui.widget

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
interface OnScrollProgressListener {
    /**
     * 开始滑动
     *
     * @param progress Int
     */
    fun onStartProgress(progress: Int)

    /**
     * 滑动中
     *
     * @param progress Int
     */
    fun onProgress(progress: Int)

    /**
     * 结束滑动
     *
     * @param progress Int
     */
    fun onStopProgress(progress: Int)
}