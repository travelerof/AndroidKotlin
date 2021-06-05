package com.hyg.permission.dialog

import android.view.View

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
interface OnApplyListener {
    fun onApply(v: View, dialog: PermissionDialog)
    fun onRefuse(v: View, dialog: PermissionDialog)
}