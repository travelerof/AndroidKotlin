package com.hyg.video.ui.controller

import android.view.View
import android.widget.RelativeLayout
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.annotation.AnimatorType

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
abstract class BaseAnimatorController(rootContainer: RelativeLayout, interPlayer: InterPlayer) :
    BaseController(rootContainer, interPlayer) {
    /**
     * 动画执行尺寸
     */
    protected var targetSize: Int = 0

    /**
     * 动画执行类型
     */
    protected var animatorType: Int = AnimatorType.ENTER

    override fun startAnimator() {
        targetSize = getAnimatorSize()
        animatorType = if (isVisible()) AnimatorType.EXIT else AnimatorType.ENTER
        getTargetView()?.visibility = View.VISIBLE
    }

    override fun endAnimator() {
        if (animatorType == AnimatorType.EXIT) {
            getTargetView()?.visibility = View.GONE
        }
    }

    /**
     * 获取执行动画尺寸
     * @return Int
     */
    protected abstract fun getAnimatorSize(): Int
}