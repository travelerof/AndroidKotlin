package com.hyg.permission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings

/**
 * @Author hanyonggang
 * @Date 2021/6/5 0005
 * @Desc
 */
class HPermissionUtils {

    companion object {
        /**
         * 应用是否有悬浮窗权限
         */
        fun hasOverlayPermission(context: Context): Boolean =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Settings.canDrawOverlays(context)
            } else {
                true
            }

        fun checkedPermission(context: Context, permission: String): Boolean =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
    }
}