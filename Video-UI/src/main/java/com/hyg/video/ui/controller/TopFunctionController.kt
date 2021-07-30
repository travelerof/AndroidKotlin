package com.hyg.video.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.R

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
class TopFunctionController(rootContainer: RelativeLayout, interPlayer: InterPlayer) :
    BaseAnimatorController(rootContainer, interPlayer) {
    private val view: View =
        LayoutInflater.from(getContext()).inflate(R.layout.item_video_top_title_layout, null)

    init {
        view.findViewById<ImageView>(R.id.video_back_iv)
            .setOnClickListener { getInterPlayer().back() }
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            getContext().resources.getDimensionPixelSize(R.dimen.dimen_45)
        )
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        addView(view, params)
    }

    override fun getAnimatorSize(): Int = view.height

    override fun getTargetView(): View = view

    override fun getTag(): String = IController.TOP_CONTROLLER_KEY
}