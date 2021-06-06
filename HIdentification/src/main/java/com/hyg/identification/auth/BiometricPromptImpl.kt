package com.hyg.identification.auth

import android.content.Context
import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.hyg.identification.AuthConstant
import com.hyg.identification.IdentificationType
import com.hyg.identification.OnIdentificationCallback
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
@RequiresApi(Build.VERSION_CODES.P)
class BiometricPromptImpl(
    context: Context,
    @IdentificationType type: Int,
    callback: OnIdentificationCallback?
) : AbstractAuthServiceService(context, type, callback) {

    private val service: BiometricPrompt =
        context.getSystemService(Context.BIOMETRIC_SERVICE) as BiometricPrompt

    private val authenticationCallback: BiometricPrompt.AuthenticationCallback =
        object : BiometricPrompt.AuthenticationCallback() {
            /**
             * 多次指纹密码验证错误有，进入该方法，并且不会在验证(短时间)
             */
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                setFailed(AuthConstant.AUTH_NEVER_FAILED_CODE, "多次认证失败，暂时不能认证")
            }

            /**
             * 指纹验证失败，可在验证，原因：该指纹没有录入到系统中
             */
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                setFailed(AuthConstant.AUTH_FAILED_CODE, "验证失败")
            }

            /**
             * 指纹验证失败，可在验证，手指过脏，或者移动过快导致
             */
            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
                setFailed(AuthConstant.AUTH_HELP_FAILED_CODE, "验证失败")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                callback?.onSucceeded(result.toString())
                callback?.onCompleted()
            }
        }

    override fun isHardwareDetected(): Boolean = true

    override fun hasEnrolledUseInfo(): Boolean  = true

    override fun auth(): IAuthService {
        BiometricPrompt.Builder(context)
            .setNegativeButton("取消",Executors.newSingleThreadExecutor(),
                { dialog, which ->  })
            .setTitle("标题")
            .setDescription("描述")
            .build()
            .authenticate(cancellationSignal,Executors.newSingleThreadExecutor(),authenticationCallback)
        return this
    }

}