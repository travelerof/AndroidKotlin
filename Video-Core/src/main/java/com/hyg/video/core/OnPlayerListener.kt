package com.hyg.video.core

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
interface OnPlayerListener {

    /**
     * 操作
     *
     * @param event Int
     */
    fun onEvent(@PlayStatus event: Int)

    /**
     * 播放错误
     *
     * @param code Int
     * @param error String
     * @param throwable Throwable?
     */
    fun onError(@PlayError code: Int, error: String, throwable: Throwable?)
}