package com.example.to3et.qrreader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRReaderViewModel: ViewModel() {
    private val _barcodeText = MutableLiveData<String>()
    val barcodeText : LiveData<String> = _barcodeText

    private val _isCameraRead = MutableLiveData<Boolean>(false)
    val isCameraRead : LiveData<Boolean> = _isCameraRead

    private val _copyText = MutableLiveData<String>()
    val copyText : LiveData<String> = _copyText

    private val _snacbkarText = MutableLiveData<String>()
    val snackbarText : LiveData<String> = _snacbkarText

    fun showBarcodeResult(barcodeText : String) {
        _isCameraRead.postValue(true)
        _barcodeText.postValue(barcodeText)
    }

    fun hideBarcodeResult() {
        _isCameraRead.postValue(false)
    }

    fun copyText() {
        _copyText.postValue(barcodeText.value)
        _snacbkarText.postValue("コピーしました")
    }
}