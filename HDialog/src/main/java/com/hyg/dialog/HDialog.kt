package com.hyg.dialog

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

/**
 * @Author 韩永刚
 * @Date 2021/06/07
 * @Desc
 */
class HDialog : BaseDialog {

    private val options: BuilderOptions
    private val tvTitle: TextView
    private val titleLine: View
    private val messageLayout: FrameLayout
    private val tvMessage: TextView
    private val messageLine: View
    private val bottomLayout: LinearLayout
    private val tvNegative: TextView
    private val functionLine: View
    private val tvPositive: TextView

    private constructor(context: Context, options: BuilderOptions) : super(context) {
        this.options = options
        val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)
        tvTitle = view.findViewById(R.id.dialog_title_tv)
        titleLine = view.findViewById(R.id.dialog_title_line_view)
        messageLayout = view.findViewById(R.id.dialog_message_layout)
        tvMessage = view.findViewById(R.id.dialog_message_tv)
        messageLine = view.findViewById(R.id.dialog_message_line_view)
        bottomLayout = view.findViewById(R.id.dialog_bottom_layout)
        tvNegative = view.findViewById(R.id.dialog_negative_tv)
        functionLine = view.findViewById(R.id.dialog_function_line)
        tvPositive = view.findViewById(R.id.dialog_positive_tv)
        setContentView(view)
        setListener()
        setOptions()
    }

    private fun setListener() {
        tvNegative.setOnClickListener {
            options.negativeClickListener?.onClick(this, it)
        }

        tvPositive.setOnClickListener {
            options.positiveClickListener?.onClick(this, it)
        }
    }

    private fun setOptions() {
        setCancelable(options.cancelable)
        setCanceledOnTouchOutside(options.touchOutside)
        setTitleOptions()
        setMessageOptions()
        setContentView()
        setPressOptions()
    }

    /**
     * 初始化标题
     */
    private fun setTitleOptions() {
        options.title?.let {
            tvTitle.visibility = View.VISIBLE
            titleLine.visibility = View.VISIBLE
            options.titleTextListener?.onText(tvTitle)
            tvTitle.text = it
        } ?: let {
            tvTitle.visibility = View.GONE
            titleLine.visibility = View.GONE
        }
    }

    private fun setMessageOptions() {
        options.message?.let {
            tvMessage.visibility = View.VISIBLE
            messageLine.visibility = View.VISIBLE
            options.messageTextListener?.onText(tvMessage)
            tvMessage.text = it
        } ?: let {
            tvMessage.visibility = View.GONE
            messageLine.visibility = View.GONE
        }
    }

    private fun setContentView() {
        options.contentView?.let {
            tvMessage.visibility = View.GONE
            messageLayout.addView(it)
        }
    }

    private fun setPressOptions() {
        if (TextUtils.isEmpty(options.negativeText) && TextUtils.isEmpty(options.positiveText)) {
            messageLine.visibility = View.GONE
            bottomLayout.visibility = View.GONE
        } else if (!TextUtils.isEmpty(options.negativeText) && TextUtils.isEmpty(options.positiveText)) {
            functionLine.visibility = View.GONE
            tvPositive.visibility = View.GONE
            tvNegative.text = options.negativeText
            options.negativeTextListener?.onText(tvNegative)
        } else if (TextUtils.isEmpty(options.negativeText) && TextUtils.isEmpty(options.positiveText)) {
            functionLine.visibility = View.GONE
            tvNegative.visibility = View.GONE
            tvPositive.text = options.negativeText
            options.positiveTextListener?.onText(tvPositive)
        } else {
            tvNegative.text = options.negativeText
            tvPositive.text = options.negativeText
            options.negativeTextListener?.onText(tvNegative)
            options.positiveTextListener?.onText(tvPositive)
        }
    }

    override fun getGravity(): Int = options.gravity

    override fun getWidth(): Int = options.width

    override fun getHeight(): Int = options.height

    override fun getAlpha(): Float = options.alpha

    override fun getWindowAnimationStyleId(): Int = options.windowAnimationStyleId

    fun setTitleText(text: CharSequence) {
        options.title = text
        tvTitle.text = options.title
    }

    fun setMessageText(text: CharSequence) {
        tvMessage.text = text
    }


    class Builder(private val context: Context) {

        private val mOptions = BuilderOptions()

        init {
            mOptions.width = context.resources.displayMetrics.widthPixels / 2 * 3
        }

        fun setWindowAnimationStyleId(windowAnimationStyleId: Int): Builder {
            mOptions.windowAnimationStyleId = windowAnimationStyleId
            return this
        }

        fun setGravity(gravity: Int): Builder {
            mOptions.gravity = gravity
            return this
        }

        fun setAlpha(alpha: Float): Builder {
            mOptions.alpha = alpha
            return this
        }

        fun setWidth(width: Int): Builder {
            mOptions.width = width
            return this
        }

        fun setHeight(height: Int): Builder {
            mOptions.height = height
            return this
        }

        fun setTitle(title: CharSequence): Builder {
            return setTitle(title, null)
        }

        fun setTitle(title: CharSequence, listener: OnTextListener?): Builder {
            mOptions.title = title
            mOptions.titleTextListener = listener
            return this
        }

        fun setMessage(message: CharSequence): Builder {
            return setMessage(message, null)
        }

        fun setMessage(message: CharSequence, listener: OnTextListener?): Builder {
            mOptions.message = message
            mOptions.messageTextListener = listener
            return this
        }

        fun setCancelable(cancel: Boolean): Builder {
            mOptions.cancelable = cancel
            return this
        }

        fun setCanceledOnTouchOutside(cancel: Boolean): Builder {
            mOptions.touchOutside = cancel
            return this
        }

        fun setContentView(contentView: View): Builder {
            mOptions.contentView = contentView
            return this
        }

        fun setContentView(resId: Int): Builder =
            setContentView(LayoutInflater.from(context).inflate(resId, null))

        fun setPositive(text: CharSequence, clickListener: OnDialogClickListener?): Builder =
            setPositive(text, null, clickListener)

        fun setPositive(
            text: CharSequence,
            listener: OnTextListener?,
            clickListener: OnDialogClickListener?
        ): Builder {
            mOptions.positiveText = text
            mOptions.positiveTextListener = listener
            mOptions.positiveClickListener = clickListener
            return this
        }

        fun setNegative(text: CharSequence, clickListener: OnDialogClickListener?): Builder =
            setNegative(text, null, clickListener)

        fun setNegative(
            text: CharSequence,
            listener: OnTextListener?,
            clickListener: OnDialogClickListener?
        ): Builder {
            mOptions.negativeText = text
            mOptions.negativeTextListener = listener
            mOptions.negativeClickListener = clickListener
            return this
        }


        fun build(): HDialog = HDialog(context, mOptions)
    }
}