package com.hyg.video.ui.controller

import android.view.Surface
import android.view.View
import android.widget.RelativeLayout
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.annotation.RendererType
import com.hyg.video.ui.widget.IRendererView
import com.hyg.video.ui.widget.OnSurfaceListener
import com.hyg.video.ui.widget.internal.RendererSurfaceView
import com.hyg.video.ui.widget.internal.RendererTextureView

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc 视频渲染控制器
 */
class RendererController : BaseController, OnSurfaceListener {
    private val iRendererView: IRendererView

    constructor(rootContainer: RelativeLayout, interPlayer: InterPlayer) : super(
        rootContainer,
        interPlayer
    ) {
        iRendererView = createRendererView()
        iRendererView.setOnSurfaceListener(this)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        addView(iRendererView.getView(), params)
    }

    private fun createRendererView(): IRendererView =
        if (getInterPlayer().getRendererType() == RendererType.SURFACE) {
            RendererSurfaceView(getContext())
        } else {
            RendererTextureView(getContext())
        }

    override fun getTargetView(): View = iRendererView.getView()

    override fun onSurface(surface: Surface) {
        getInterPlayer().getPlayer().setSurface(surface)
    }

    override fun getTag(): String = IController.RENDERER_CONTROLLER_KEY
}