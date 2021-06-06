package com.hyg.permission

import androidx.annotation.IntDef
import androidx.annotation.StringDef
import java.util.jar.Manifest

/**
 * @Author hanyonggang
 * @Date 2021/6/5 0005
 * @Desc
 */
@StringDef(Permission.WINDOW_OVERLAY,Permission.NOTIFICATION)
@Retention(AnnotationRetention.SOURCE)
annotation class Permission {
    companion object{
        /**
         * 悬浮窗
         */
        const val WINDOW_OVERLAY = "android.permission.SYSTEM_ALERT_WINDOW"

        /**
         * 通知
         */
        const val NOTIFICATION = "android.permission.NOTIFICATION"
    }
}