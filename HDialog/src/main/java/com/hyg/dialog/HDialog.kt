package com.hyg.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View

/**
 * @Author 韩永刚
 * @Date 2021/06/07
 * @Desc
 */
class HDialog : BaseDialog {

    private constructor(context: Context) : super(context)

    private constructor(context: Context, themeId: Int) : super(context, themeId)

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
            if (themeId == -1) HDialog(context) else HDialog(context, themeId)
    }
}