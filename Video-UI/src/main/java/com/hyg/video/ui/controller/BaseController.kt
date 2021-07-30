package com.hyg.video.ui.controller

import android.view.View
import android.widget.RelativeLayout
import com.hyg.video.core.HPlayer
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.UIConstant

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
abstract class BaseController(
    protected val rootContainer: RelativeLayout,
    protected val interPlayer: InterPlayer
) : IController {

    protected val player: HPlayer = interPlayer.getPlayer()

    /**
     * 控制器是否被挂起
     */
    private var pause: Boolean = false

    protected fun getContext() = rootContainer.context

    protected fun addView(view: View) {
        rootContainer.addView(view)
    }

    protected fun addView(view: View, params: RelativeLayout.LayoutParams) {
        rootContainer.addView(view, params)
    }

    override fun isPause(): Boolean = pause

    override fun setPause(pause: Boolean) {
        this.pause = pause
    }

    override fun perform(event: Int) {
        if (isPause()) {
            return
        }
        when (event) {
            UIConstant.SHOW -> show()
            UIConstant.HIDE -> hide()
        }
    }

    protected fun show() {
        if (getTargetView().visibility != View.VISIBLE) {
            getTargetView().visibility = View.VISIBLE
        }
    }

    protected fun hide() {
        if (getTargetView().visibility == View.VISIBLE) {
            getTargetView().visibility = View.GONE
        }
    }

    override fun release() {

    }
}