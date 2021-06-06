package com.hyg.permission

/**
 * @Author 韩永刚
 * @Date 2021/06/04
 * @Desc
 */
interface OnPermissionCallback {
    fun onSucceeded(requestCode : Int, permissions : Array<String>)
    fun onFailed(requestCode : Int, permissions : Array<String>)
}