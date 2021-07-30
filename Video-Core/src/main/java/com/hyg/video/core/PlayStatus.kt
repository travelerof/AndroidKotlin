package com.hyg.video.core

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc 播放状态码
 */
@IntDef(
    PlayStatus.FIRST_ADD_VIDEO_SOURCE,
    PlayStatus.PREPARING,
    PlayStatus.PREPARE_COMPLETE,
    PlayStatus.PLAYING,
    PlayStatus.PLAY_STOP,
    PlayStatus.BUFFEREDING,
    PlayStatus.BUFFERED_COMPLETE,
    PlayStatus.RESET,
    PlayStatus.PLAY_COMPLETE
)
@Retention(AnnotationRetention.SOURCE)
annotation class PlayStatus {
    companion object {

        const val PLAY_INIT = 0
        /**
         * 首次添加资源
         */
        const val FIRST_ADD_VIDEO_SOURCE = 1

        /**
         * 视频资源准备中
         */
        const val PREPARING = 2

        /**
         * 视频资源准备完成
         */
        const val PREPARE_COMPLETE = 3

        /**
         * 视频播放中
         */
        const val PLAYING = 4

        /**
         * 停止播放
         */
        const val PLAY_STOP = 5

        /**
         * 缓冲中
         */
        const val BUFFEREDING = 6

        /**
         * 缓冲完成
         */
        const val BUFFERED_COMPLETE = 7

        /**
         * 重置播放器
         */
        const val RESET = 8

        /**
         * 播放完成
         */
        const val PLAY_COMPLETE = 9
    }
}