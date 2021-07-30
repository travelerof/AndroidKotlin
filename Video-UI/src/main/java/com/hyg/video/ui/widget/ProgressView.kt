package com.hyg.video.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.hyg.video.ui.R

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
class ProgressView : View {
    private var max: Int = 100
    private var progress: Int = 0
    private var secondaryProgress: Int = 0
    private var defaultColor: Int = Color.parseColor("#ffffff")
    private var progressColor: Int = Color.parseColor("#ffffff")
    private var secondaryProgressColor: Int = Color.parseColor("#ffffff")
    private var thumbColor: Int = Color.parseColor("#ffffff")
    private var progressHeight: Int = 15
    private var progressMargin: Int = 10
    private val paint: Paint
    private var enTouch: Boolean = true
    private var intervalSize: Int = 0
    private var onScrollProgressListener: OnScrollProgressListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView)
        max = array.getInt(R.styleable.ProgressView_maxProgress, 100)
        progress = array.getInt(R.styleable.ProgressView_progress, 0)
        secondaryProgress = array.getInt(R.styleable.ProgressView_secondaryProgress, 0)
        defaultColor = array.getColor(
            R.styleable.ProgressView_defaultProgressColor,
            context.getColor(R.color.white)
        )
        progressColor = array.getColor(
            R.styleable.ProgressView_progressColor,
            context.getColor(R.color.color_6200EE)
        )
        secondaryProgressColor = array.getColor(
            R.styleable.ProgressView_secondaryProgressColor,
            context.getColor(R.color.color_03DAC5)
        )
        thumbColor = array.getColor(
            R.styleable.ProgressView_progressThumbColor,
            context.getColor(R.color.white)
        )
        progressMargin = array.getDimensionPixelSize(
            R.styleable.ProgressView_progressMargin,
            context.resources.getDimensionPixelSize(R.dimen.dimen_8)
        )
        progressHeight = array.getDimensionPixelSize(
            R.styleable.ProgressView_progressHeight,
            context.resources.getDimensionPixelSize(R.dimen.dimen_3)
        )
        enTouch = array.getBoolean(R.styleable.ProgressView_enProgressTouch, true)
        array.recycle()

        paint = Paint()
        paint.isAntiAlias = true
        paint.strokeWidth = progressHeight.toFloat()
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (!enTouch) {
            return super.onTouchEvent(event)
        }
        event?.let {
            var x = it.x
            if (x < progressMargin) {
                x = progressMargin.toFloat()
            }
            if (x > width - progressMargin) {
                x = (width - progressMargin).toFloat()
            }
            val offset = (x - progressMargin) / intervalSize.toFloat()
            val progress: Int = (max * offset).toInt()
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    this.progress = progress
                    invalidate()
                    onScrollProgressListener?.onStartProgress(progress)
                }
                MotionEvent.ACTION_MOVE -> {
                    if (progress != this.progress) {
                        this.progress = progress
                        invalidate()
                        onScrollProgressListener?.onProgress(progress)
                    } else {

                    }
                }
                MotionEvent.ACTION_UP -> {
                    this.progress = progress
                    onScrollProgressListener?.onStopProgress(progress)
                }
                else -> {

                }
            }
        }
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec)
        if (heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), getDefaultHeight())
        }
        intervalSize = measuredWidth - progressMargin * 2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.strokeWidth = progressHeight.toFloat()
        paint.color = defaultColor
        canvas?.drawLine(
            progressMargin.toFloat(), (height / 2).toFloat(),
            (width - progressMargin).toFloat(), (height / 2).toFloat(), paint
        )
        //缓冲
        paint.color = secondaryProgressColor
        canvas?.drawLine(
            progressMargin.toFloat(),
            (height / 2).toFloat(),
            (progressMargin + computePosition(secondaryProgress)).toFloat(),
            (height / 2).toFloat(),
            paint
        )
        //进度
        val progressX = (progressMargin + computePosition(progress)).toFloat()
        paint.color = progressColor
        canvas?.drawLine(
            progressMargin.toFloat(), (height / 2).toFloat(),
            progressX, (height / 2).toFloat(), paint
        )
        if (enTouch) {
            paint.color = thumbColor
            paint.strokeWidth = (progressHeight * 4).toFloat()
            canvas?.drawPoint(progressX, (height / 2).toFloat(), paint)
        }
    }

    fun setMax(max: Int) {
        this.max = max
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }

    fun setSecondaryProgress(progress: Int) {
        this.secondaryProgress = progress
        invalidate()
    }

    fun getMax(): Int = max

    fun getProgress(): Int = getProgress()

    fun getSecondaryProgress(): Int = secondaryProgress

    fun setDefaultColor(color: Int) {
        defaultColor = color
        invalidate()
    }

    fun setProgressColor(color: Int) {
        progressColor = color
        invalidate()
    }

    fun setSecondaryProgressColor(color: Int) {
        secondaryProgressColor = color
        invalidate()
    }

    fun setThumbColor(color: Int) {
        thumbColor = color
        invalidate()
    }

    fun setProgressHeight(height: Int) {
        progressHeight = height
        invalidate()
    }

    fun setEnTouch(touch: Boolean) {
        enTouch = touch
        invalidate()
    }

    fun setOnScrollProgressListener(scrollProgressListener: OnScrollProgressListener) {
        onScrollProgressListener = scrollProgressListener
    }

    private fun getDefaultHeight(): Int =
        if (enTouch) {
            progressHeight * 4
        } else {
            progressHeight
        } + context.resources.getDimensionPixelSize(R.dimen.dimen_2)

    private fun computePosition(progress: Int): Int {
        val offset: Float = progress / max.toFloat()
        return (intervalSize * offset).toInt()
    }
}