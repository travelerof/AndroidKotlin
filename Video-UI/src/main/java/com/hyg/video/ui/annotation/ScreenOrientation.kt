package com.hyg.video.ui.annotation

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc 屏幕方向
 */
@IntDef(ScreenOrientation.PORTRAIT,ScreenOrientation.LANDSCAPE,ScreenOrientation.SCREEN_SMALL)
@Retention(AnnotationRetention.SOURCE)
annotation class ScreenOrientation{
    companion object{
        /**
         * 竖屏
         */
        const val PORTRAIT = 0

        /**
         * 横屏
         */
        const val LANDSCAPE = 1

        /**
         * 小屏
         */
        const val SCREEN_SMALL = 2
    }
}
