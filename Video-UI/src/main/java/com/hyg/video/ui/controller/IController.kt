package com.hyg.video.ui.controller

import android.view.View
import com.hyg.video.ui.annotation.AnimatorDirection
import com.hyg.video.ui.annotation.AnimatorType

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
interface IController {
    companion object {
        const val TOP_CONTROLLER_KEY = "top_controller"
        const val ERROR_CONTROLLER_KEY = "error_controller"
        const val GESTURE_CONTROLLER_KEY = "gesture_controller"
        const val LOADING_CONTROLLER_KEY = "loading_controller"
        const val RENDERER_CONTROLLER_KEY = "renderer_controller"
        const val PLAY_COMPLETE_CONTROLLER_KEY = "play_complete_controller"
        const val BOTTOM_CONTROLLER_KEY = "bottom_complete_controller"
        const val BOTTOM_PORTRAIT_CONTROLLER_KEY = "bottom_portrait_complete_controller"
        const val BOTTOM_LANDSCAPE_CONTROLLER_KEY = "bottom_landscape_complete_controller"
    }

    /**
     * 执行操作码
     *
     * @param event Int
     */
    fun perform(event: Int)

    /**
     * 设置是否挂起
     *
     * @param pause Boolean
     */
    fun setPause(pause: Boolean)

    /**
     * 是否被挂起
     *
     * @return Boolean
     */
    fun isPause(): Boolean

    /**
     * 释放控制器资源
     */
    fun release()

    /**
     * 是否可执行动画
     *
     * @return Boolean
     */
    fun isAnimator(): Boolean = false

    /**
     * 设置是否可执行动画
     *
     * @param animator Boolean
     */
    fun setAnimatorStatus(animator: Boolean) {

    }

    /**
     * 开始执行动画
     */
    fun startAnimator() {

    }

    /**
     * 动画执行中
     *
     * @param offset Float
     */
    fun animator(offset: Float) {

    }

    /**
     * 动画执行结束
     */
    fun endAnimator() {

    }

    /**
     * 获取目标view
     *
     * @return View
     */
    fun getTargetView(): View

    /**
     * 当前控制器是否可见
     *
     * @return Boolean
     */
    fun isVisible(): Boolean = getTargetView()?.visibility == View.VISIBLE

    /**
     * 获取当前tag
     *
     * @return String
     */
    fun getTag(): String

}