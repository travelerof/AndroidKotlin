package com.hyg.permission

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/06/04
 * @Desc
 */
@IntDef(PermissionType.NORMAL, PermissionType.SPECIAL)
@Retention(AnnotationRetention.SOURCE)
annotation class PermissionType {
    companion object {
        const val NORMAL = 0
        const val SPECIAL = 1
    }
}

