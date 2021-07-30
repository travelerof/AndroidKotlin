package com.hyg.web.bridge

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * @Author 韩永刚
 * @Date 2021/06/11
 * @Desc
 */
@SuppressLint("SetJavaScriptEnabled")
class BridgeWebView(context: Context, attr: AttributeSet?, defStyleAttr: Int) :
    WebView(context, attr, defStyleAttr) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)

    init {
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false
        settings.javaScriptEnabled = true
        webViewClient = generateWebViewClient()
    }

    private fun generateWebViewClient(): WebViewClient = BridgeWebViewClient()

    fun send(data: String){

    }
}