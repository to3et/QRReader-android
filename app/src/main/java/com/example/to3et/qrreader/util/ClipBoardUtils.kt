package com.example.to3et.qrreader.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class ClipBoardUtils {
    companion object {
        fun copyText(context : Context, text : String) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipdata = ClipData.newPlainText("BarcodeResultTest", text)

            clipboard.primaryClip = clipdata
        }
    }
}