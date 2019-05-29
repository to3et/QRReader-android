package com.example.to3et.qrreader.barcoderesult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.to3et.qrreader.model.SimpleBarcodeResult

class BarcodeResultViewModelFactory(
        val barcodeResult : SimpleBarcodeResult) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BarcodeResultViewModel(barcodeResult) as T
    }
}