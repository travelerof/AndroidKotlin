package com.hyg.video.ui.controller

import android.annotation.SuppressLint
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
internal class BottomPortraitFunctionController(rootContainer: RelativeLayout, interPlayer: InterPlayer) :
    BaseController(rootContainer, interPlayer) {
    private val view: View =
        LayoutInflater.from(getContext()).inflate(R.layout.item_video_portrait_bottom_layout, null)

    init {
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            getContext().resources.getDimensionPixelSize(R.dimen.dimen_45)
        )
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        addView(view, params)
    }

    override fun getTargetView(): View = view
    override fun getTag(): String = IController.BOTTOM_PORTRAIT_CONTROLLER_KEY
}