package com.example.to3et.qrreader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRReaderViewModel : ViewModel() {
    private val _barcodeResult = MutableLiveData<String>()
    val barcodeResult : LiveData<String> = _barcodeResult

    fun notifyBarcodeResult(barcodeResult : String) {
        _barcodeResult.postValue(barcodeResult)
    }
}