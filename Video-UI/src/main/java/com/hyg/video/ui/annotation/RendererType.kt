package com.hyg.video.ui.annotation

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc 渲染器类型
 */
@IntDef(RendererType.SURFACE, RendererType.TEXTURE)
@Retention(AnnotationRetention.SOURCE)
annotation class RendererType {
    companion object {
        /**
         * SurfaceView
         */
        const val SURFACE = 0

        /**
         * TextureView
         */
        const val TEXTURE = 1
    }
}
