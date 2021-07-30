package com.hyg.video.ui.annotation

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc 动画执行方向
 */
@IntDef(AnimatorDirection.X, AnimatorDirection.Y)
@Retention(AnnotationRetention.SOURCE)
annotation class AnimatorDirection {
    companion object {
        /**
         * x轴方向
         */
        const val X = 0

        /**
         * y轴方向
         */
        const val Y = 1
    }
}
