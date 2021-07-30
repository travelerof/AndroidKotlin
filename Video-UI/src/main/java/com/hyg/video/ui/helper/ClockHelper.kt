package com.hyg.video.ui.helper

import android.os.Handler
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.UIConstant
import com.hyg.video.ui.annotation.PlaybackSpeed
import java.util.*

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc
 */
class ClockHelper(interPlayer: InterPlayer) {

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    /**
     * 加速因子
     */
    private var speed: PlaybackSpeed = PlaybackSpeed.SPEED_1

    /**
     * 是否正在执行定时器
     */
    private var clocking: Boolean = false

    private val clockHandler = Handler(Handler.Callback {
        if (it.what == UIConstant.HandlerCode.CLOCK_CODE) {
            interPlayer.onClock()
            true
        } else {
            false
        }
    })

    fun start() {
        if (clocking) {
            return
        }
        if (timer === null) {
            timer = Timer()
        }
        if (timerTask === null) {
            timerTask = object : TimerTask() {
                override fun run() {

                }

            }
        }
        clockHandler.removeCallbacksAndMessages(null)
        timer?.schedule(timerTask, 0, speed.value())
        clocking = true
    }

    fun stop() {
        clockHandler.removeCallbacksAndMessages(null)
        timer?.cancel()
        timer = null
        timerTask?.cancel()
        timerTask = null
        clocking = false
    }

    fun reStart() {
        stop()
        start()
    }

    fun updateSpeed(speed: PlaybackSpeed) {
        if (this.speed != speed) {
            this.speed = speed
            reStart()
        }
    }

}