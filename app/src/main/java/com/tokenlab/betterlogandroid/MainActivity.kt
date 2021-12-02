package com.tokenlab.betterlogandroid

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tokenlab.betterlog.BetterLog

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SampleLog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Log with default values D/BetterLog: ---
        BetterLog.d()
        BetterLog.d(msg="Quick debug message")

        try {
            allExamples()
        } catch (error: Exception) {
            BetterLog.e(TAG, "error with message", error)
            // Log with default values
            BetterLog.w(tr=error)
        }

        exampleFunction {
            BetterLog.d(msg="Inside lambda")
        }

        findViewById<TextView>(R.id.instructionText).setOnClickListener {
            BetterLog.d(msg="Text clicked!")
        }

    }

    private fun allExamples() {
        BetterLog.e(TAG, "Sample error message")
        BetterLog.w(TAG, "Sample warn message")
        BetterLog.i(TAG, "Sample info message")
        BetterLog.d(TAG, "Sample debug message")
        BetterLog.v(TAG, "Sample verbose message")

        exampleFunction {
            BetterLog.d(TAG, "Lambda inside other function")
            BetterLog.path(TAG, "Viewing path for this log")
        }

        throw RuntimeException("Sample for exceptions")
    }

    private fun exampleFunction(fn: () -> Unit) {
        fn()
    }
}