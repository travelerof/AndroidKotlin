package com.hyg.log

/**
 * @Author 韩永刚
 * @Date 2021/06/03
 * @Desc 日志输入
 */
interface Printer {
    /**
     *
     */
    fun print(priority : Int, tag : String, message : String)
}