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
class BottomLandscapeFunctionController(rootContainer: RelativeLayout, interPlayer: InterPlayer) :
    BaseController(rootContainer, interPlayer) {
    private val view: View =
        LayoutInflater.from(getContext()).inflate(R.layout.item_video_landscape_bottom_layout, null)

    init {
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        addView(view, params)
    }

    override fun getTargetView(): View = view
    override fun getTag(): String = IController.BOTTOM_LANDSCAPE_CONTROLLER_KEY
}