package com.hyg.log

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

/**
 * @Author 韩永刚
 * @Date 2021/06/03
 * @Desc 日志打印
 */
object HLog{
    /**
     * 默认tag
     */
    private const val TAG = "HLog"

    private const val START =
        "╔═══════════════════════════════════════════════════════════════════════════════════════"

    private const val END =
        "╚═══════════════════════════════════════════════════════════════════════════════════════"
    private const val CENTER = "║ "

    private val LINE_SEPARATOR = System.getProperty("line.separator")

    /**
     * 是否为debug模式
     */
    private var debug = false

    /**
     * 日志输出接口
     */
    private var printer: Printer? = null

    /**
     * 设置是否为debug
     */
    fun debug(debug: Boolean) {
        HLog.debug = debug
    }

    fun debug(debug: Boolean, printer: Printer?) {
        HLog.debug = debug
        HLog.printer = printer
    }

    fun v(message: String): Int = v(TAG, message)

    fun v(tag: String, message: String): Int = v(tag, message, null)

    fun v(tag: String, message: String, throwable: Throwable?): Int =
        print(Log.VERBOSE, tag, message, throwable)

    fun d(message: String): Int = d(TAG, message)

    fun d(tag: String, message: String): Int = d(tag, message, null)

    fun d(tag: String, message: String, throwable: Throwable?): Int =
        print(Log.DEBUG, tag, message, throwable)

    fun i(message: String): Int = i(TAG, message)

    fun i(tag: String, message: String): Int = i(tag, message, null)

    fun i(tag: String, message: String, throwable: Throwable?): Int =
        print(Log.INFO, tag, message, throwable)

    fun w(message: String): Int = w(TAG, message)

    fun w(tag: String, message: String): Int = w(tag, message, null)

    fun w(tag: String, message: String, throwable: Throwable?): Int =
        print(Log.WARN, tag, message, throwable)

    fun e(message: String): Int = e(TAG, message)

    fun e(tag: String, message: String): Int = e(tag, message, null)

    fun e(tag: String, message: String, throwable: Throwable?): Int =
        print(Log.ERROR, tag, message, throwable)

    fun j(message: String): Int = j(TAG, message)

    fun j(tag: String, message: String): Int {
        if (!debug) return -1
        synchronized(this) {
            var msg = try {
                toJson(message)
            } catch (e: Nothing) {
                message
            }
            printer?.print(Log.WARN, tag, msg)
            Log.w(tag, START)
            msg = "json：$LINE_SEPARATOR$msg"
            val split = msg.split(LINE_SEPARATOR)
            for (item: String in split) {
                Log.w(tag, CENTER + item)
            }
            Log.w(tag, END)
            return 0
        }
    }

    private fun print(priority: Int, tag: String, message: String, throwable: Throwable?): Int {
        if (!debug) return -1
        synchronized(this) {
            val msg = message + '\n' + Log.getStackTraceString(throwable)
            printer?.print(priority, tag, message)
            return Log.println(priority, tag, msg)
        }
    }

    fun toJson(message: String): String = when {
        message.startsWith("{") -> {
            JSONObject(message).toString(4)
        }
        message.startsWith("[") -> {
            JSONArray(message).toString(4)
        }
        else -> {
            message
        }
    }
}