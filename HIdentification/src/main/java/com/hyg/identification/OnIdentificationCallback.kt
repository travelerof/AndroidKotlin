package com.hyg.identification

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
interface OnIdentificationCallback {

    /**
     * 开始认证
     */
    fun onStarted()
    /**
     * 认证成功
     */
    fun onSucceeded(msg: String)

    /**
     * 认证失败
     */
    fun onFailed(code: Int, msg: String)

    /**
     * 认证完成
     */
    fun onCompleted()
}