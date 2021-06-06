package com.hyg.identification.auth

import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import com.hyg.identification.AuthConstant
import com.hyg.identification.IdentificationType
import com.hyg.identification.OnIdentificationCallback

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
@RequiresApi(Build.VERSION_CODES.M)
class FingerPrintImpl(
    context: Context,
    @IdentificationType type: Int,
    callback: OnIdentificationCallback?
) : AbstractAuthServiceService(context, type, callback) {

    private val service: FingerprintManager =
        context.getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager


    /**
     * 指纹验证结果
     */
    private val authenticationCallback: FingerprintManager.AuthenticationCallback =
        object : FingerprintManager.AuthenticationCallback() {
            /**
             * 多次指纹密码验证错误有，进入该方法，并且不会在验证(短时间)
             */
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                setFailed(AuthConstant.AUTH_NEVER_FAILED_CODE,"多次认证失败，暂时不能认证")
            }

            /**
             * 指纹验证失败，可在验证，原因：该指纹没有录入到系统中
             */
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                setFailed(AuthConstant.AUTH_FAILED_CODE,"验证失败")
            }

            /**
             * 指纹验证失败，可在验证，手指过脏，或者移动过快导致
             */
            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
                setFailed(AuthConstant.AUTH_HELP_FAILED_CODE,"验证失败")
            }

            /**
             * 指纹验证成功
             */
            override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                callback?.onSucceeded(result.toString())
                callback?.onCompleted()
            }
        }

    override fun isHardwareDetected(): Boolean = service.isHardwareDetected
    override fun hasEnrolledUseInfo(): Boolean = service.hasEnrolledFingerprints()
    override fun auth(): IAuthService {
        if (!isHardwareDetected()) {
            setFailed(AuthConstant.HARDWARE_ERROR_CODE,"硬件不支持")
            return this
        }
        if (!hasEnrolledUseInfo()) {
            setFailed(AuthConstant.ENROLLED_FINGERPRINT_ERROR_CODE,"手机中没有设置指纹信息")
            return this
        }
        service.authenticate(
            null,
            cancellationSignal,
            0,
            authenticationCallback,
            null
        )
        return this
    }




}


