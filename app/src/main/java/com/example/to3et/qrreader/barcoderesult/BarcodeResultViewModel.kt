package com.example.to3et.qrreader.barcoderesult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.to3et.qrreader.Event
import com.example.to3et.qrreader.model.SimpleBarcodeResult

class BarcodeResultViewModel(
        val barcodeResult: SimpleBarcodeResult) : ViewModel() {

    private val _displayValue = MutableLiveData<String>()
    val displayValue : LiveData<String>
        get() = _displayValue

    private val _copyText = MutableLiveData<Event<String>>()
    val copyText : LiveData<Event<String>>
        get() = _copyText

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText : LiveData<Event<String>>
        get() = _snackbarText

    init {
        _displayValue.postValue(barcodeResult.text)
    }

    fun copyText() {
        _displayValue.value?.let {
            _copyText.postValue(Event(it))
            _snackbarText.postValue(Event("コピーしました"))
        }
    }
}