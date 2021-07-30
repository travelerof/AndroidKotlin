package com.hyg.dialog

import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * @Author 韩永刚
 * @Date 2021/06/07
 * @Desc
 */
class BuilderOptions {
    /**
     * dialog宽高，默认为-1，使用默认值
     */
    var width: Int = -1
    var height: Int = WindowManager.LayoutParams.WRAP_CONTENT

    /**
     * 弹出位置
     */
    var gravity: Int = Gravity.CENTER

    var alpha: Float = 1.0f

    var windowAnimationStyleId: Int = R.style.HDialog_Center_Anim
    /**
     * 消息内容
     */
    var message: CharSequence? = null
    var messageTextListener: OnTextListener? = null
    var title: CharSequence? = null
    var titleTextListener: OnTextListener? = null
    var cancelable: Boolean = true
    var touchOutside: Boolean = true

    var contentView: View? = null
    var positiveText: CharSequence? = null
    var positiveTextListener: OnTextListener? = null
    var positiveClickListener: OnDialogClickListener? = null

    var negativeText: CharSequence? = null
    var negativeTextListener: OnTextListener? = null
    var negativeClickListener: OnDialogClickListener? = null
}
