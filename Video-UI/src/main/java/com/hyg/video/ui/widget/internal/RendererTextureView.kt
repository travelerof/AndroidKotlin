package com.hyg.video.ui.widget.internal

import android.content.Context
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.view.Surface
import android.view.TextureView
import android.view.View
import com.hyg.video.core.utils.VLog
import com.hyg.video.ui.widget.IRendererView
import com.hyg.video.ui.widget.OnSurfaceListener

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
internal class RendererTextureView : TextureView, IRendererView,
    TextureView.SurfaceTextureListener {

    private val TAG = "RendererTextureView"

    private var rendererSurfaceTexture: SurfaceTexture? = null
    private var rendererSurface: Surface? = null
    private var surfaceListener: OnSurfaceListener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        surfaceTextureListener = this
    }

    override fun getView(): View = this

    override fun release() {
        rendererSurface?.release()
        rendererSurfaceTexture?.release()
        rendererSurface = null
        rendererSurfaceTexture = null
    }

    override fun setOnSurfaceListener(surfaceListener: OnSurfaceListener) {
        this.surfaceListener = surfaceListener
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        VLog.v(TAG, "渲染器可见》》》》onSurfaceTextureAvailable")
        rendererSurfaceTexture?.let {
            setSurfaceTexture(it)
        } ?: let {
            rendererSurfaceTexture = surface
            rendererSurface = Surface(rendererSurfaceTexture)
            surfaceListener?.onSurface(rendererSurface!!)
        }
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {

    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        return true
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

    }
}