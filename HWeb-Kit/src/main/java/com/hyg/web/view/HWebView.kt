package com.hyg.web.view

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import java.util.jar.Attributes

/**
 * @Author 韩永刚
 * @Date 2021/06/11
 * @Desc
 */
class HWebView(context: Context, attr: AttributeSet?, defStyleAttr: Int) :
    WebView(context, attr, defStyleAttr) {
    constructor(context: Context) : this(context,null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    init {

    }
}