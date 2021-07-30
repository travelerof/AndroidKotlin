package com.hyg.video.ui.controller

import android.view.View
import android.widget.RelativeLayout
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.annotation.ScreenOrientation

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
class BottomFunctionController(rootContainer: RelativeLayout, interPlayer: InterPlayer) :
    BaseController(rootContainer, interPlayer) {
    private val bottomController: IController

    init {
        bottomController = createBottomController()
    }

    private fun createBottomController(): IController =
        if (getInterPlayer().getScreenOrientation() == ScreenOrientation.LANDSCAPE) {
            BottomLandscapeFunctionController(getRootContainer(), getInterPlayer())
        } else {
            BottomPortraitFunctionController(getRootContainer(), getInterPlayer())
        }

    override fun perform(event: Int) {
        super.perform(event)
        if (isPause()) {
            return
        }
        bottomController.perform(event)
    }
    override fun getTargetView(): View = bottomController.getTargetView()

    override fun getTag(): String = IController.BOTTOM_CONTROLLER_KEY
}