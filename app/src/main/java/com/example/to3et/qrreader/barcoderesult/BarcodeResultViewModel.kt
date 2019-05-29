package com.example.to3et.qrreader.barcoderesult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.to3et.qrreader.model.SimpleBarcodeResult

class BarcodeResultViewModel(
        val barcodeResult: SimpleBarcodeResult) : ViewModel() {

    private val _displayValue = MutableLiveData<String>()
    val displayValue : LiveData<String> = _displayValue

    private val _copyText = MutableLiveData<String>()
    val copyText : LiveData<String> = _copyText

    private val _snacbkarText = MutableLiveData<String>()
    val snackbarText : LiveData<String> = _snacbkarText

    init {
        _displayValue.postValue(barcodeResult.text)
    }

    fun copyText() {
        _copyText.postValue(_displayValue.value)
        _snacbkarText.postValue("コピーしました")
    }
}