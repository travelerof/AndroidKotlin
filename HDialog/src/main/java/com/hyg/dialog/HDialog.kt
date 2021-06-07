package com.hyg.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Build
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

    private val tvTitle:TextView
    private val titleLine: View
    private val messageLayout: FrameLayout
    private val tvMessage: TextView
    private val messageLine: View
    private val bottomLayout: LinearLayout
    private val tvNegative: TextView
    private val functionLine:View
    private val tvPositive: TextView
    private constructor(context: Context, options: BuilderOptions) : this(
        context,
        options,
        R.style.HDialog
    )

    private constructor(context: Context, options: BuilderOptions, themeId: Int) : super(
        context,
        themeId
    ) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_dialog_layout, null)
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
        setCancelable(options.cancelable)
        setCanceledOnTouchOutside(options.touchOutside)
        setListener()

    }

    private fun setListener(){
        tvNegative.setOnClickListener {

        }

        tvPositive.setOnClickListener {

        }
    }


    fun setTitleText(text: CharSequence){
        tvTitle.text = text
    }

    fun setMessageText(text: CharSequence){
        tvMessage.text = text
    }


    class Builder(private val context: Context, private val themeId: Int = 10) {

        private val mOptions = BuilderOptions()

        constructor(context: Context) : this(context, -1)

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


        fun build(): HDialog =
            if (themeId == -1) HDialog(context,mOptions) else HDialog(context, mOptions,themeId)
    }
}