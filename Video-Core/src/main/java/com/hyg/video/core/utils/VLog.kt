package com.hyg.video.core.utils

import android.util.Log

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
class VLog {
    companion object {
        @JvmStatic
        private var debug: Boolean = false

        @JvmStatic
        fun debug(debug: Boolean) {
            Companion.debug = debug
        }

        @JvmStatic
        fun v(tag: String, msg: String) {
            v(tag, msg, null)
        }

        @JvmStatic
        fun v(tag: String, msg: String, throwable: Throwable?) {
            if (debug) {
                Log.v(tag, msg, throwable)
            }
        }

        @JvmStatic
        fun e(tag: String, msg: String) {
            e(tag, msg, null)
        }

        @JvmStatic
        fun e(tag: String, msg: String, throwable: Throwable?) {
            if (debug) {
                Log.e(tag, msg, throwable)
            }
        }
    }
}