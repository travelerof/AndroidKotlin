package com.hyg.video.ui.controller

import android.widget.RelativeLayout
import com.hyg.video.core.PlayStatus
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.UIConstant

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc
 */
abstract class BaseProgressController(rootContainer: RelativeLayout, interPlayer: InterPlayer): BaseController(rootContainer, interPlayer) {
    override fun perform(event: Int) {
        super.perform(event)
        if (isPause()) {
            return
        }
        when (event) {
            PlayStatus.PREPARE_COMPLETE -> prepared()
            PlayStatus.PLAYING -> playing()
            PlayStatus.PLAY_STOP,PlayStatus.PLAY_COMPLETE -> stop()
            UIConstant.UPDATE_PROGRESS -> updateProgress()
            else -> {
            }
        }
    }

    protected fun prepared(){

    }
    protected abstract fun playing()

    protected abstract fun stop()

    fun progress(){
        val currentPosition = player.getCurrentPosition()
    }
    protected abstract fun progress(currentPosition: Long, bufferedPosition: Long)
}