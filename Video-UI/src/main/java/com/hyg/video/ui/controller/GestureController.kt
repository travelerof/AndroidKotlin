package com.hyg.video.ui.controller

import android.view.View
import android.widget.RelativeLayout
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.widget.TouchView

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
internal class GestureController(rootContainer: RelativeLayout, interPlayer: InterPlayer) :
    BaseController(rootContainer, interPlayer) {
    private val touchView = TouchView(getContext())

    override fun perform(event: Int) {
        if (isPause()) {
            return
        }
    }

    override fun getTargetView(): View = touchView

    override fun getTag(): String = IController.GESTURE_CONTROLLER_KEY
}