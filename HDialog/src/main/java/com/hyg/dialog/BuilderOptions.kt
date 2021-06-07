package com.hyg.dialog

import android.view.Gravity
import android.view.View

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
    var height: Int = -1

    /**
     * 弹出位置
     */
    var gravity: Int = Gravity.CENTER
    var message: CharSequence? = null
    var messageTextListener: OnTextListener? = null
    var title: CharSequence? = null
    var titleTextListener: OnTextListener? = null
    var cancelable: Boolean = false
    var touchOutside: Boolean = false

    var contentView: View? = null
    var positiveText: CharSequence? = null
    var positiveTextListener: OnTextListener? = null
    var positiveClickListener: OnDialogClickListener? = null

    var negativeText: CharSequence? = null
    var negativeTextListener: OnTextListener? = null
    var negativeClickListener: OnDialogClickListener? = null
}
