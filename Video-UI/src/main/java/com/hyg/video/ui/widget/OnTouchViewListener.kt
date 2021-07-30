package com.hyg.video.ui.widget

import com.hyg.video.ui.annotation.TouchDirection

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc
 */
interface OnTouchViewListener {
    /**
     * 获取当前播放进度百分比
     * @return Float
     */
    fun getPlayPositionPercent(): Float

    /**
     * 获取音量百分比
     * @return Float
     */
    fun getVolumePercent(): Float

    /**
     * 获取亮度百分比
     * @return Float
     */
    fun getBrightnessPercent(): Float

    /**
     * 单击
     */
    fun onClick()

    /**
     * 双击
     */
    fun onDoubleClick()

    /**
     * 长按
     */
    fun onLongClick()

    /**
     * 开始触摸
     * @param direction Int
     */
    fun onStartTouch(@TouchDirection direction: Int)

    /**
     * 触摸中
     * @param direction Int
     * @param offset Float
     */
    fun onTouch(@TouchDirection direction: Int, offset: Float)

    /**
     * 停止触摸
     *
     * @param direction Int
     */
    fun onStopTouch(@TouchDirection direction: Int)
}
