package com.hyg.video.ui.helper

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Handler
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.UIConstant
import com.hyg.video.ui.controller.IController

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
class AnimatorHelper(
    interPlayer: InterPlayer,
    /**
     * 动画最大值
     */
    private val max: Int,
    /**
     * 动画执行时长
     */
    private val duration: Long,
    /**
     * 动画定时关闭
     */
    private val clock: Long
) {
    /**
     * 是否正在执行动画
     */
    private var animatoring: Boolean = false

    /**
     * 动画对象
     */
    private var valueAnimator: ValueAnimator? = null

    /**
     * 执行动画载体
     */
    private var targetControllers: MutableList<IController> = interPlayer.getControllerManager().filterAnimators(true)

    private val handler = Handler(Handler.Callback {
        when (it.what) {
            UIConstant.HandlerCode.ANIMATOR_CODE -> {
                if (isShow()) {
                    start()
                }
                true
            }
            else -> false
        }
    })

    /**
     * 开始执行动画
     */
    public fun start() {
        //是否正在执行动画
        if (animatoring) {
            return
        }
        if (valueAnimator === null) {
            valueAnimator = createAnimator()
        }
        valueAnimator?.start()
    }

    /**
     * 创建属性动画
     *
     * @return ValueAnimator
     */
    private fun createAnimator(): ValueAnimator {
        val animator = ValueAnimator.ofInt(0, max)
        animator.addUpdateListener {
            animator(it.animatedValue as Int)
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                startAnimator()
            }

            override fun onAnimationEnd(animation: Animator?) {
                endAnimator()
            }
        })
        animator.duration = duration
        return animator
    }

    private fun startAnimator() {
        animatoring = true
        for (item in targetControllers) {
            item.startAnimator()
        }
    }

    private fun animator(value: Int) {
        val offset = value / max.toFloat()
        for (item in targetControllers) {
            item.animator(offset)
        }
    }

    private fun endAnimator() {
        animatoring = false
        for (item in targetControllers) {
            item.endAnimator()
        }
        startClock()
    }

    fun startClock() {
        if (!isShow()) {
            return
        }
        handler.removeCallbacksAndMessages(null)
        handler.sendEmptyMessageDelayed(UIConstant.HandlerCode.ANIMATOR_CODE, clock)
    }


    fun isShow(): Boolean {
        var count = 0
        for (item in targetControllers) {
            if (item.isVisible()) {
                count++
            }
        }
        return count >= targetControllers.size
    }

    fun release() {
        animatoring = false
        valueAnimator?.cancel()
        valueAnimator = null
        targetControllers.clear()
    }
}