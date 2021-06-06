package com.hyg.identification

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
class AuthConstant {
    companion object{
        /**
         * SDK版本过低,必须大于6.0
         */
        const val SDK_ERROR_CODE = -1

        /**
         * 没有权限
         */
        const val PERMISSION_ERROR_CODE = -2

        /**
         * 硬件不支持
         */
        const val HARDWARE_ERROR_CODE = -3

        /**
         * 手机中没有录入过指纹
         */
        const val ENROLLED_FINGERPRINT_ERROR_CODE = -4

        /**
         * 多次认证失败，不能在认证（短时间）
         */
        const val AUTH_NEVER_FAILED_CODE = -5

        /**
         * 指纹验证失败，可在验证，手指过脏，或者移动过快导致
         */
        const val AUTH_HELP_FAILED_CODE = -6

        /**
         * 指纹验证失败，可在验证，原因：该指纹没有录入到系统中
         */
        const val AUTH_FAILED_CODE = -7
    }
}