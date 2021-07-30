package com.hyg.video.ui.annotation

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc 动画执行类型
 */
@IntDef(AnimatorType.ENTER, AnimatorType.EXIT)
@Retention(AnnotationRetention.SOURCE)
annotation class AnimatorType{
    companion object {
        /**
         * 执行进入动画
         */
        const val ENTER = 0

        /**
         * 执行退出动画
         */
        const val EXIT = 1
    }
}
