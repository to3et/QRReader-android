package com.example.to3et.qrreader.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeAtOnce(owner: LifecycleOwner, observe: (value: T) -> Unit) = apply {
    observe(owner, Observer {
            removeObserver(observe)
            observe(it)
    })
}