package com.hyg.video.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.R

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
class PlayCompleteController:BaseController {
    private val view: View
    constructor(rootContainer: RelativeLayout, interPlayer: InterPlayer):super(rootContainer, interPlayer){
        view = LayoutInflater.from(getContext()).inflate(R.layout.item_video_play_complete_layout, null)
    }

    override fun perform(event: Int) {
        if (isPause()) {
            return
        }
    }

    override fun getTargetView(): View = view

    override fun getTag(): String = IController.PLAY_COMPLETE_CONTROLLER_KEY
}