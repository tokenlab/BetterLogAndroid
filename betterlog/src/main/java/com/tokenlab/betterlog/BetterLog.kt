package com.tokenlab.betterlog

import android.util.Log

object BetterLog {

    private const val DEFAULT_TAG = "BetterLog"
    private const val DEFAULT_MESSAGE = "---"
    private val moreInfoFor = listOf("invoke", "accept")

    @JvmStatic
    fun e(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.e(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    fun w(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.w(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    fun i(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.i(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    fun d(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.d(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    fun v(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE, tr: Throwable? = null) {
        Log.v(tag, handleMessage(msg), tr)
    }

    @JvmStatic
    fun path(tag: String = DEFAULT_TAG, msg: String = DEFAULT_MESSAGE) {
        val stackTrace = Exception().stackTrace
        val caller = stackTrace.caller()

        val fileStackTrace = stackTrace.filter { it.fileName == caller.fileName }
        val path = fileStackTrace.joinToString("\n\tat ") { it.getLink() }

        Log.v(tag,"$msg | on $path")
    }

    private fun handleMessage(message: String): String {
        return try {
            val stackTrace = Exception().stackTrace
            val caller = stackTrace.caller()
            if (moreInfoFor.contains(caller.methodName)) {
                "$message | ${caller.getMoreInfo(stackTrace)}"
            } else {
                "$message | ${caller.getLink()}"
            }
        } catch (e: Exception) {
            "Unhandled message: $message"
        }
    }

    private fun Array<StackTraceElement>.caller(): StackTraceElement {
        val thisClass = this.first().className
        val indexOfCaller = indexOfLast { it.className == thisClass } + 1
        return this[indexOfCaller]
    }

    private fun StackTraceElement.getLink() = ".${methodName} (${fileName}:${lineNumber})"

    private fun StackTraceElement.getMoreInfo(stackTrace: Array<StackTraceElement>): String {
        val nextCaller = stackTrace.firstOrNull {
            it.fileName == this.fileName && !moreInfoFor.contains(it.methodName)
        } ?: return this.getLink()

        return "${nextCaller.methodName}.${this.getLink()}"
    }

}