package com.hyg.video.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.hyg.video.core.HPlayer
import com.hyg.video.ui.annotation.PlaybackSpeed
import com.hyg.video.ui.annotation.RendererType
import com.hyg.video.ui.annotation.ScreenOrientation
import com.hyg.video.ui.manager.IControllerManager

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
interface InterPlayer {

    fun getContext(): Context

    /**
     * 通过上下文获取对应activity
     *
     * @param context Context?
     * @return Activity?
     */
    fun getActivity(context: Context?): Activity? {
        if (context === null) {
            return null
        }
        if (context is Activity) {
            return context
        } else if (context is ContextWrapper) {
            return getActivity(context.baseContext)
        }
        return null
    }

    fun getPlayer(): HPlayer

    fun play()

    fun pause()

    fun isPlaying(): Boolean

    /**
     * 更新播放速度
     *
     * @param speed PlaybackSpeed
     */
    fun updateSpeed(speed: PlaybackSpeed)

    /**
     * 获取渲染器类型
     *
     * @return Int
     */
    @RendererType
    fun getRendererType(): Int

    /**
     * 获取控制器管理器
     *
     * @return IControllerManager
     */
    fun getControllerManager(): IControllerManager

    /**
     * 获取当前屏幕方向发生变化
     *
     * @return Int
     */
    @ScreenOrientation
    fun getScreenOrientation(): Int

    /**
     * 屏幕方向变化
     *
     * @param orientation Int
     */
    fun onScreenChanged(@ScreenOrientation orientation: Int)

    /**
     * 点击返回键
     */
    fun back() {
        if (getScreenOrientation() == ScreenOrientation.LANDSCAPE) {
            onScreenChanged(ScreenOrientation.PORTRAIT)
            return
        }
        getActivity(getContext())?.finish()
    }

    /**
     * 定时器
     */
    fun onClock()
}