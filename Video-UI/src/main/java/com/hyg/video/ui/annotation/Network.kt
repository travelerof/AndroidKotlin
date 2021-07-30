package com.hyg.video.ui.annotation

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc 网络连接状态
 */
@IntDef(Network.INIT, Network.NONE, Network.WIFI, Network.MOBILE)
@Retention(AnnotationRetention.SOURCE)
annotation class Network {
    companion object {
        /**
         * 初始化状态，尚未检测
         */
        const val INIT = 0

        /**
         * 无网络链接
         */
        const val NONE = 1

        /**
         * WIFI
         */
        const val WIFI = 2

        /**
         * 移动数据
         */
        const val MOBILE = 3
    }
}
