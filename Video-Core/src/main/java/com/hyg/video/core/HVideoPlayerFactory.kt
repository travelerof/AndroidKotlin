package com.hyg.video.core

import android.content.Context

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
abstract class HVideoPlayerFactory<T : HVideoPlayer> {

    abstract fun create(context: Context): T
}