package com.hyg.identification

import android.Manifest
import android.content.Context
import android.os.Build
import com.hyg.identification.auth.AbstractAuthServiceService
import com.hyg.identification.auth.BiometricPromptImpl
import com.hyg.identification.auth.FingerPrintImpl
import com.hyg.identification.auth.IAuthService
import com.hyg.permission.HPermission
import com.hyg.permission.HPermissionUtils
import com.hyg.permission.OnPermissionCallback

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
class AuthRequest(
    private val context: Context,
    @IdentificationType private val type: Int,
    private val callback: OnIdentificationCallback?
) : OnPermissionCallback {
    private val service: IAuthService? = createIdentificationService()


    /**
     * 创建生物识别service
     */
    private fun createIdentificationService(): IAuthService? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return BiometricPromptImpl(context, type, callback)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return FingerPrintImpl(context, type, callback)
        }
        return null
    }

    fun auth() {
        callback?.onStarted()
        if (service === null) {
            setFailed(AuthConstant.SDK_ERROR_CODE,"当前手机SDK版本过低")
            return
        }
        val permission = getPermission()
        if (!HPermissionUtils.checkedPermission(context, permission)) {
            requestPermission(permission)
            return
        }
        service.auth()
    }

    /**
     * 获取权限名
     */
    private fun getPermission(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) Manifest.permission.USE_BIOMETRIC else Manifest.permission.USE_FINGERPRINT

    /**
     * 申请权限
     */
    private fun requestPermission(permission: String) {
        HPermission.with(context)
            .requestCode(100)
            .permission(permission)
            .callback(this)
            .build()
            .request()
    }

    override fun onSucceeded(requestCode: Int, permissions: Array<String>) {
        auth()
    }

    override fun onFailed(requestCode: Int, permissions: Array<String>) {
        setFailed(AuthConstant.PERMISSION_ERROR_CODE,"没有权限")
    }

    private fun setFailed(code: Int, msg: String) {
        callback?.onFailed(code, msg)
        callback?.onCompleted()
    }
}