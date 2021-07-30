package com.hyg.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager

/**
 * @Author 韩永刚
 * @Date 2021/06/07
 * @Desc
 */
open class BaseDialog: Dialog {
    constructor(context: Context) : this(context, R.style.BaseDialog)

    constructor(context: Context,themeId: Int) : super(context,themeId){
        val params: WindowManager.LayoutParams? = window?.attributes
        params?.width = getWidth()
        params?.height = getHeight()
        params?.alpha = getAlpha()
        params?.gravity = getGravity()
        val animId = getWindowAnimationStyleId()
        if (animId > 0) {
            params?.windowAnimations = animId
        }
        window?.attributes = params
    }

    /**
     * dialog宽度，默认为屏幕宽度2/3
     */
    protected open fun getWidth(): Int = context.resources.displayMetrics.widthPixels / 3 * 2

    /**
     * dialog高度，默认为自适应
     */
    protected open fun getHeight(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    /**
     * dialog弹出位置，默认在中间
     */
    protected open fun getGravity(): Int = Gravity.CENTER

    /**
     * dialog透明度，默认不透明
     */
    protected open fun getAlpha(): Float = 1.0f

    /**
     * dialog弹出动画，默认不设置
     */
    protected open fun getWindowAnimationStyleId(): Int = R.style.HDialog_Center_Anim
}