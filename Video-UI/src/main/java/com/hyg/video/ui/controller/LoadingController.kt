package com.hyg.video.ui.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.R

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc 正在缓冲控制器
 */
class LoadingController(rootContainer: RelativeLayout, interPlayer: InterPlayer): BaseController(rootContainer, interPlayer) {
    private val view: View = LayoutInflater.from(getContext()).inflate(R.layout.item_video_loading_layout, null)

    override fun getTargetView(): View = view

    override fun getTag(): String = IController.LOADING_CONTROLLER_KEY
}