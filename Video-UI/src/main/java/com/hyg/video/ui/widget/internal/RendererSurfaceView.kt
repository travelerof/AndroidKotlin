package com.hyg.video.ui.widget.internal

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import com.hyg.video.ui.widget.IRendererView
import com.hyg.video.ui.widget.OnSurfaceListener

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
internal class RendererSurfaceView:SurfaceView,SurfaceHolder.Callback, IRendererView {

    private var surfaceListener: OnSurfaceListener? = null

    constructor(context: Context):this(context,null)

    constructor(context: Context, attrs: AttributeSet?):super(context, attrs){
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        surfaceListener?.onSurface(holder.surface)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    override fun getView(): View = this

    override fun release() {
        surfaceListener = null
        holder.removeCallback(this)
    }

    override fun setOnSurfaceListener(surfaceListener: OnSurfaceListener) {
        this.surfaceListener = surfaceListener
    }
}