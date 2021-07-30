package com.hyg.video.core.ijk

import android.content.Context
import com.hyg.video.core.HVideoPlayerFactory

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
class IjkVideoPlayerFactory : HVideoPlayerFactory<IjkVideoPlayer>() {
    override fun create(context: Context): IjkVideoPlayer = IjkVideoPlayer(context)
}