package com.hyg.video.core

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc
 */
@IntDef(PlayError.LOAD_ERROR, PlayError.PLAY_ERROR)
@Retention(AnnotationRetention.SOURCE)
annotation class PlayError {
    companion object {
        const val LOAD_ERROR = -1
        const val PLAY_ERROR = -2
    }
}
