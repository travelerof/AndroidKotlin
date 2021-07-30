package com.hyg.video.core.exo

import android.content.Context
import com.hyg.video.core.HVideoPlayerFactory

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
class ExoVideoPlayerFactory : HVideoPlayerFactory<ExoVideoPlayer>() {
    override fun create(context: Context): ExoVideoPlayer = ExoVideoPlayer(context)
}