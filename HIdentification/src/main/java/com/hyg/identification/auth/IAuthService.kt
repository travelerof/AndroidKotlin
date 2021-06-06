package com.hyg.identification.auth

/**
 * @Author hanyonggang
 * @Date 2021/6/6 0006
 * @Desc
 */
interface IAuthService {
    fun auth(): IAuthService

    fun cancel()

}