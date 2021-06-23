package com.tokenlab.betterlog

import android.util.Log

object BetterLog {

    private const val DEFAULT_TAG = "BetterLog"
    private const val DEFAULT_MESSAGE = "---"

    @JvmStatic
    @JvmOverloads
    fun e(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.e(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    @JvmOverloads
    fun w(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.w(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    @JvmOverloads
    fun i(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.i(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    @JvmOverloads
    fun d(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.d(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    @JvmOverloads
    fun v(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.v(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    @JvmOverloads
    fun q(msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        d(DEFAULT_TAG, msg, tr)
    }

    private fun handleMessage(message: String): String {
        return try {
            val stackTrace = Error().stackTrace
            val thisMethod = stackTrace.first()
            val indexOfLast = stackTrace.indexOfLast { it.fileName == thisMethod.fileName }
            val caller = stackTrace[indexOfLast + 1]

            "$message | on ${caller.methodName} (${caller.fileName}:${caller.lineNumber})"
        } catch (e: Exception) {
            "Unhandled message: $message"
        }
    }

}