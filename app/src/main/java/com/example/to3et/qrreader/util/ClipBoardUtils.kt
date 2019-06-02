package com.example.to3et.qrreader.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class ClipBoardUtils {
    companion object {
        fun copyText(context : Context?, text : String?) {
            text?: return
            context?: return
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            clipboard.primaryClip = ClipData.newPlainText("QRReader", text)
        }
    }
}