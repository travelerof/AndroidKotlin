package com.hyg.identification.auth

import android.content.Context
import android.os.CancellationSignal
import com.hyg.identification.IdentificationType
import com.hyg.identification.OnIdentificationCallback

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
abstract class AbstractAuthServiceService(
    protected val context: Context,
    @IdentificationType protected val type: Int,
    protected val callback: OnIdentificationCallback?
) : IAuthService {

    protected val cancellationSignal: CancellationSignal = CancellationSignal()


    /**
     * 判断是否硬件支持
     */
    protected abstract fun isHardwareDetected(): Boolean

    /**
     * 是否已经录入过指纹/人脸
     */
    protected abstract fun hasEnrolledUseInfo(): Boolean

    override fun cancel() {
        cancellationSignal.cancel()
    }

    protected fun setFailed(code: Int, msg: String) {
        callback?.onFailed(code, msg)
        callback?.onCompleted()
    }
}