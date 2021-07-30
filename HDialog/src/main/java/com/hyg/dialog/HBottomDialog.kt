package com.hyg.dialog

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * @Author 韩永刚
 * @Date 2021/06/08
 * @Desc 底部弹出dialog
 */
class HBottomDialog private constructor(context: Context,private val options: BuilderOptions):BaseDialog(context) {
    private val titleLayout: RelativeLayout
//    private val tvCancel: TextView
//    private val tvFinish: TextView
//    private val tvTitle: TextView
//    private
    init {
        val view: View = layoutInflater.inflate(R.layout.dialog_bottom_layout,null)
        titleLayout = view.findViewById(R.id.dialog_bottom_title_layout)
    }
    override fun getGravity(): Int = Gravity.BOTTOM

    class Builder(private val context: Context){

        private val options: BuilderOptions = BuilderOptions()

        fun setWidth(width: Int):Builder{
            options.width = width
            return this
        }
        fun setHeight(height: Int): Builder{
            options.height = height
            return this
        }

        fun setTitle(text: CharSequence):Builder {
            return setTitle(text,null)
        }

        fun setContentView(contentView: View):Builder{
            return this
        }
        fun setTitle(text: CharSequence, listener: OnTextListener?): Builder {
            options.title = text
            options.titleTextListener = listener
            return this
        }

        fun setCancel(text: CharSequence, clickListener: OnDialogClickListener?): Builder{
            return setCancel(text,null, clickListener)
        }

        fun setCancel(text: CharSequence, textListener: OnTextListener?, clickListener: OnDialogClickListener?): Builder{
            options.negativeText = text
            options.negativeTextListener = textListener
            options.negativeClickListener = clickListener
            return this
        }

        fun setFinish(text: CharSequence, clickListener: OnDialogClickListener?): Builder{
            return setFinish(text,null, clickListener)
        }

        fun setFinish(text: CharSequence, textListener: OnTextListener?, clickListener: OnDialogClickListener?): Builder{
            options.positiveText = text
            options.positiveTextListener = textListener
            options.positiveClickListener = clickListener
            return this
        }

        fun build() = HBottomDialog(context,options)

    }
}