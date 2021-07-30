package com.hyg.web.bridge

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.*

/**
 * @Author 韩永刚
 * @Date 2021/06/11
 * @Desc  https://blog.csdn.net/niuba123456/article/details/81177567
 */
class BridgeWebViewClient:WebViewClient() {
    /**
     * 重定向时回调
     *
     * @param view WebView
     * @param request WebResourceRequest
     * @return Boolean
     */
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }

    /**
     * 开始加载时
     *
     * @param view WebView
     * @param url String
     * @param favicon Bitmap
     */
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    /**
     * 网页加载完成
     *
     * @param view WebView
     * @param url String
     */
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    /**
     * 加载url资源
     *
     * @param view WebView
     * @param url String
     */
    override fun onLoadResource(view: WebView?, url: String?) {
        super.onLoadResource(view, url)
    }

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        return super.shouldInterceptRequest(view, request)
    }

    /**
     * 访问出错时
     *
     * @param view WebView
     * @param request WebResourceRequest
     * @param error WebResourceError
     */
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
    }

    /**
     * post请求
     * @param view WebView
     * @param dontResend Message
     * @param resend Message
     */
    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?) {
        super.onFormResubmission(view, dontResend, resend)
    }

    /**
     * 更新数据时
     * @param view WebView
     * @param url String
     * @param isReload Boolean
     */
    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        super.doUpdateVisitedHistory(view, url, isReload)
    }

    /**
     * 加载ssl错误
     * @param view WebView
     * @param handler SslErrorHandler
     * @param error SslError
     */
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
    }

    /**
     * auth请求回调
     * @param view WebView
     * @param handler HttpAuthHandler
     * @param host String
     * @param realm String
     */
    override fun onReceivedHttpAuthRequest(
        view: WebView?,
        handler: HttpAuthHandler?,
        host: String?,
        realm: String?
    ) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm)
    }

    /**
     * 按键时执行
     * @param view WebView
     * @param event KeyEvent
     * @return Boolean
     */
    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        return super.shouldOverrideKeyEvent(view, event)
    }

    /**
     * webview尺寸变化
     *
     * @param view WebView
     * @param oldScale Float
     * @param newScale Float
     */
    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
        super.onScaleChanged(view, oldScale, newScale)
    }

    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
        return super.onRenderProcessGone(view, detail)
    }
}