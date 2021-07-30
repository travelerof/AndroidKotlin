package com.hyg.video.core.exo

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.os.Handler
import android.view.Surface
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.*
import com.google.android.exoplayer2.video.VideoListener
import com.hyg.video.core.HVideoPlayer
import com.hyg.video.core.PlayError
import com.hyg.video.core.PlayStatus
import java.io.IOException

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
class ExoVideoPlayer(context: Context) : HVideoPlayer(context) {
    private var exoPlayer: SimpleExoPlayer? = null

    private var preparing: Boolean = false

    private val metadataRetriever = MediaMetadataRetriever()
    private val eventListener = object : Player.EventListener {
        override fun onPlaybackStateChanged(state: Int) {
            when (state) {
                Player.STATE_BUFFERING -> setPlayCode(PlayStatus.BUFFEREDING)
                Player.STATE_READY -> setPlayCode(PlayStatus.BUFFERED_COMPLETE)
                Player.STATE_ENDED -> {
                    setPlayCode(PlayStatus.PLAY_COMPLETE)
                }
                Player.STATE_IDLE -> {
                    setPlayCode(PlayStatus.PLAY_INIT)
                }
            }
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            setPlayCode(if (isPlaying) PlayStatus.PLAYING else PlayStatus.PLAY_STOP)
        }
    }
    private val videoListener = object : VideoListener {
        override fun onVideoSizeChanged(
            width: Int,
            height: Int,
            unappliedRotationDegrees: Int,
            pixelWidthHeightRatio: Float
        ) {
            rendererListener?.onVideoSizeChanged(width, height)
        }

        override fun onSurfaceSizeChanged(width: Int, height: Int) {
            rendererListener?.onSurfaceSizeChanged(width, height)
        }
    }

    private val mediaSourceEventListener = object : MediaSourceEventListener {
        override fun onLoadStarted(
            windowIndex: Int,
            mediaPeriodId: MediaSource.MediaPeriodId?,
            loadEventInfo: LoadEventInfo,
            mediaLoadData: MediaLoadData
        ) {
            if (!preparing) {
                preparing = true
                setPlayCode(PlayStatus.PREPARING)
            }
        }

        override fun onLoadError(
            windowIndex: Int,
            mediaPeriodId: MediaSource.MediaPeriodId?,
            loadEventInfo: LoadEventInfo,
            mediaLoadData: MediaLoadData,
            error: IOException,
            wasCanceled: Boolean
        ) {
            setError(PlayError.LOAD_ERROR, "加载失败", error)
        }

        override fun onDownstreamFormatChanged(
            windowIndex: Int,
            mediaPeriodId: MediaSource.MediaPeriodId?,
            mediaLoadData: MediaLoadData
        ) {
            if (preparing) {
                preparing = false
                setPlayCode(PlayStatus.PREPARE_COMPLETE)
                prepareComplete = true
            }
        }
    }

    override fun initPlayer() {
        exoPlayer = SimpleExoPlayer.Builder(getContext()).build()
        exoPlayer?.addListener(eventListener)
        exoPlayer?.addVideoListener(videoListener)
    }

    private fun nextMediaSource(): MediaSource {
        val source = getVideoSources()[getPlayIndex()]
        val mediaSource =
            DefaultMediaSourceFactory(getContext()).createMediaSource(MediaItem.fromUri(source.uri()))
        mediaSource.addEventListener(Handler(), mediaSourceEventListener)
        return mediaSource
    }

    override fun prepare() {
        if (preparing) {
            return
        }
        exoPlayer?.let {
            it.addMediaSource(nextMediaSource())
            it.prepare()
            prepareComplete = false
        }
    }

    override fun play() {
        exoPlayer?.run {
            if (!isPlaying) {
                play()
            }
        }
    }

    override fun pause() {
        exoPlayer?.run {
            if (isPlaying) {
                pause()
            }
        }
    }

    override fun stop() {
        exoPlayer?.stop(false)
    }

    override fun isPlaying(): Boolean =
        exoPlayer?.isPlaying ?: false

    override fun reset() {
        exoPlayer?.stop(true)
    }

    override fun seekTo(currentPosition: Long) {
        exoPlayer?.seekTo(currentPosition)
    }

    override fun getCurrentPosition(): Long =
        exoPlayer?.currentPosition ?: 0

    override fun getDuration(): Long =
        exoPlayer?.duration ?: 0

    override fun setSpeed(speed: Float) {
        exoPlayer?.setPlaybackParameters(PlaybackParameters(speed))
    }

    override fun getSpeed(): Float =
        exoPlayer?.playbackParameters?.speed ?: 1f

    override fun setVolume(volume: Float) {
        exoPlayer?.volume = volume
    }

    override fun getVolume(): Float =
        exoPlayer?.volume ?: 1f

    override fun getBufferedPosition(): Long =
        exoPlayer?.bufferedPosition ?: 0

    override fun setSurface(surface: Surface?) {
        exoPlayer?.setVideoSurface(surface)
    }

    override fun getFrameAtTime(currentPosition: Long): Bitmap? =
        metadataRetriever.getFrameAtTime(currentPosition)

    override fun release() {
        super.release()
        exoPlayer?.run {
            release()
            removeListener(eventListener)
            removeVideoListener(videoListener)
        }
        preparing = false
        prepareComplete = false
    }
}