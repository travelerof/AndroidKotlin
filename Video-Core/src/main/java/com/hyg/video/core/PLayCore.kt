package com.hyg.video.core

import androidx.annotation.IntDef

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
@IntDef(PLayCore.EXO, PLayCore.IJK)
@Retention(AnnotationRetention.SOURCE)
annotation class PLayCore {
    companion object {
        const val EXO = 0
        const val IJK = 1
    }
}
