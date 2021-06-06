package com.hyg.identification

import androidx.annotation.IntDef

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
@IntDef(IdentificationType.FINGERPRINT,IdentificationType.FACE)
@Retention(AnnotationRetention.SOURCE)
annotation class IdentificationType{
    companion object{
        /**
         * 指纹
         */
        const val FINGERPRINT = 0

        /**
         * 人脸
         */
        const val FACE = 1
    }
}
