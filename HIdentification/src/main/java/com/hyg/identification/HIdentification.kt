package com.hyg.identification

import android.content.Context

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
class HIdentification {
    companion object {
        fun with(context: Context): IdentificationBuilder = IdentificationBuilder(context)
    }

    class IdentificationBuilder(context: Context) {
        private val mContext = context

        /**
         * 认证类型，默认指纹
         */
        private var type: Int = IdentificationType.FINGERPRINT

        private var callback: OnIdentificationCallback? = null

        fun type(@IdentificationType type: Int): IdentificationBuilder{
            this.type = type
            return this
        }

        fun callback(callback: OnIdentificationCallback): IdentificationBuilder {
            this.callback = callback
            return this
        }

        fun build(): AuthRequest = AuthRequest(mContext,type, callback)
    }
}