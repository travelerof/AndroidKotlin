package com.hyg.video.ui

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
class UIConstant {
    companion object{
        const val SHOW = 1000
        const val HIDE = 1001
        const val UPDATE_PROGRESS = 1002
    }
    class HandlerCode{
        companion object{
            /**
             * 动画
             */
            const val ANIMATOR_CODE = 5000

            /**
             * 更新进度
             */
            const val CLOCK_CODE = 5001
        }
    }
}