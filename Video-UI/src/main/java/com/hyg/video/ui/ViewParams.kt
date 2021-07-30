package com.hyg.video.ui

import android.media.Image
import com.hyg.video.core.PLayCore
import com.hyg.video.ui.annotation.RendererType
import com.hyg.video.ui.annotation.ScreenOrientation

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc
 */
class ViewParams {

    /**
     * 内核
     */
    @PLayCore
    var playCore: Int = PLayCore.EXO

    /**
     * 渲染类型
     */
    @RendererType
    var renderer: Int = RendererType.TEXTURE

    /**
     * 动画最大值
     */
    var maxAnimatorValue: Int = 100

    /**
     * 动画时长
     */
    var animatorDuration: Long = 300

    /**
     * 定时隐藏时长
     */
    var animatorClock: Long = 5000

    /**
     * 屏幕方向
     */
    @ScreenOrientation
    var screenOrientation: Int = ScreenOrientation.PORTRAIT
}