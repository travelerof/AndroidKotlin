package com.hyg.video.ui.annotation

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
annotation class TouchDirection{
    companion object{
        /**
         * 无滑动操作
         */
        const val NONE = 0

        /**
         * 滑动进度
         */
        const val PROGRESS = 1

        /**
         * 滑动亮度
         */
        const val BRIGHTNESS = 2

        /**
         * 滑动音量
         */
        const val VOLUME = 3
    }
}
