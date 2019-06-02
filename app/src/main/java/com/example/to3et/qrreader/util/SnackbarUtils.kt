package com.example.to3et.qrreader.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

class SnackbarUtils {
    companion object {
        fun showSnackbar(v : View?, text : String?) {
            text?: return
            v?: return
            Snackbar.make(v, text, Snackbar.LENGTH_SHORT).show()
        }
    }
}