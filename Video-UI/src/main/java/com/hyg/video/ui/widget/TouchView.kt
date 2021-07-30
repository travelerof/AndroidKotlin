package com.hyg.video.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.hyg.video.ui.annotation.TouchDirection
import kotlin.math.abs

/**
 * @Author 韩永刚
 * @Date 2021/07/30
 * @Desc
 */
class TouchView : View {

    private val gestureDetector: GestureDetector

    private var direction: Int = TouchDirection.NONE

    private var verticalSize: Int = 0
    private var horizontalSize: Int = 0

    /**
     * 滑动区域
     */
    private val interval = Interval()

    /**
     * 按下时坐标
     */
    private var downX: Float = 0f
    private var downY: Float = 0f

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            e?.let {
                downX = it.x
                downY = it.y
            }
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            touchViewListener?.onClick()
            return super.onSingleTapConfirmed(e)
        }

        override fun onLongPress(e: MotionEvent?) {
            touchViewListener?.onLongClick()
            super.onLongPress(e)
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            touchViewListener?.onDoubleClick()
            return super.onDoubleTap(e)
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            e2?.let {
                scroll(it, distanceX, distanceY)
            }

            return true
        }
    }

    private var touchViewListener: OnTouchViewListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        gestureDetector = GestureDetector(context, gestureListener)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val b = gestureDetector.onTouchEvent(event)
        event?.let {
            if (it.action == MotionEvent.ACTION_UP && direction != TouchDirection.NONE) {
                touchViewListener?.onStopTouch(direction)
                direction = TouchDirection.NONE
            }
        }
        return b
    }

    fun setOnTouchViewListener(touchViewListener: OnTouchViewListener) {
        this.touchViewListener = touchViewListener
    }

    private fun scroll(
        e: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ) {
        val currentX = e.x
        val currentY = e.y
        when (direction) {
            TouchDirection.NONE -> {
                if (abs(distanceX) >= abs(distanceY)) {
                    direction = TouchDirection.PROGRESS
                } else {
                    if (downX >= width / 2) {
                        direction = TouchDirection.VOLUME
                    } else {
                        direction = TouchDirection.BRIGHTNESS
                    }
                }
                initInterval()
                touchViewListener?.onStartTouch(direction)
            }
            TouchDirection.PROGRESS -> {
                touchViewListener?.onTouch(direction, getProgressOffset(currentX))
            }
            TouchDirection.VOLUME, TouchDirection.BRIGHTNESS -> {
                touchViewListener?.onTouch(direction, getVolumeBrightnessOffset(currentY))
            }
        }
    }

    private fun initInterval() {
        interval.percent = getPercent()
        val verticalSize = height / 3 * 2
        val horizontalSize = width / 3 * 2
        when (direction) {
            TouchDirection.VOLUME, TouchDirection.BRIGHTNESS -> {
                interval.max = downY + verticalSize * interval.percent
                if (interval.max > height) {
                    interval.max = height.toFloat()
                }
                interval.min = interval.max - verticalSize
            }
            else -> {
                interval.min = downX - horizontalSize * interval.percent
                if (interval.min < 0) {
                    interval.min = 0f
                }
                interval.max = interval.min + horizontalSize
            }
        }
    }

    private fun getPercent(): Float =
        touchViewListener?.let {
            if (direction == TouchDirection.VOLUME) {
                it.getVolumePercent()
            } else if (direction == TouchDirection.BRIGHTNESS) {
                it.getBrightnessPercent()
            } else {
                it.getPlayPositionPercent()
            }
        } ?: let { 0f }

    private fun getProgressOffset(currentX: Float): Float =
        when {
            currentX <= interval.min -> {
                0f
            }
            currentX >= interval.max -> {
                1f
            }
            else -> {
                (currentX - interval.min) / (interval.max - interval.min)
            }
        }

    private fun getVolumeBrightnessOffset(currentY: Float): Float =
        when {
            currentY <= interval.min -> {
                1f
            }
            currentY >= interval.max -> {
                0f
            }
            else -> {
                1 - (currentY - interval.min) / (interval.max - interval.min)
            }
        }

    private class Interval {
        /**
         * 当前百分比
         */
        var percent: Float = 0f
        var min: Float = 0f
        var max: Float = 0f
    }
}
