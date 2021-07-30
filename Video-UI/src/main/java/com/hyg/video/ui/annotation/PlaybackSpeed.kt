package com.hyg.video.ui.annotation

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc 播放速度
 */

enum class PlaybackSpeed(val tag: String) {
    SPEED_0_5("0.5") {
        override fun value(): Long = 1500
    },
    SPEED_0_7_5("0.75") {
        override fun value(): Long = 1250
    },
    SPEED_1("1") {
        override fun value(): Long = 1000
    },
    SPEED_1_5("1.5") {
        override fun value(): Long = 800
    },
    SPEED_2("2") {
        override fun value(): Long = 650
    };

    abstract fun value(): Long
}
