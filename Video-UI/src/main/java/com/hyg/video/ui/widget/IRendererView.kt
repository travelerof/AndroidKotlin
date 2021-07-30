package com.hyg.video.ui.widget

import android.view.View

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
interface IRendererView {
    fun getView(): View

    fun release()

    fun setOnSurfaceListener(surfaceListener: OnSurfaceListener)
}