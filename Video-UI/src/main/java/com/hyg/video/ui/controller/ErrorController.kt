package com.hyg.video.ui.controller

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.R
import java.util.function.IntConsumer

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
class ErrorController(rootContainer: RelativeLayout, interPlayer: InterPlayer):BaseController(rootContainer, interPlayer) {
    private val view: View = LayoutInflater.from(getContext()).inflate(R.layout.item_video_error_layout, null)

    override fun getTargetView(): View = view

    override fun getTag(): String = IController.ERROR_CONTROLLER_KEY

}