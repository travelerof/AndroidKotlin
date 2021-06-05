package com.hyg.permission.request

/**
 * @Author 韩永刚
 * @Date 2021/06/04
 * @Desc
 */
interface IRequest {

    fun request()

    fun addExecuteListener(listener: IExecuteListener)

    fun getTag(): String

}