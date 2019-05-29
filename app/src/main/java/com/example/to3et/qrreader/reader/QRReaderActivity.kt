package com.example.to3et.qrreader.reader

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.to3et.qrreader.R
import com.example.to3et.qrreader.barcoderesult.BarcodeResultActivity
import com.example.to3et.qrreader.barcoderesult.BarcodeResultFragment
import com.example.to3et.qrreader.databinding.ActivityQrreaderBinding
import com.example.to3et.qrreader.model.SimpleBarcodeResult
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import permissions.dispatcher.*

@RuntimePermissions
class QRReaderActivity : AppCompatActivity() {
    private lateinit var mQRReaderView: DecoratedBarcodeView

    private val viewModel: QRReaderViewModel by lazy {
        ViewModelProviders.of(this).get(QRReaderViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityQrreaderBinding>(this, R.layout.activity_qrreader)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        setSupportActionBar(binding.toolbar)
        setupCamera()
    }

    override fun onResume() {
        super.onResume()
        resumeCameraWithPermissionCheck()
    }

    override fun onPause() {
        super.onPause()
        pauseCamera()
    }

    override fun onBackPressed() {
        var fragment = supportFragmentManager.findFragmentByTag(BarcodeResultFragment.TAG)
        if (fragment == null || !fragment.isVisible ) {
            finish()
        } else {
            viewModel.hideBarcodeResult()
        }
    }

    private fun setupCamera() {
        mQRReaderView = findViewById(R.id.decoratedBarcodeView)
        mQRReaderView.decodeContinuous(object: BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result ?: return
                pauseCamera()
                startActivity(
                        BarcodeResultActivity.intent(
                        this@QRReaderActivity,
                                SimpleBarcodeResult(result.rawBytes, result.text)))
            }
            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
            }
        })
    }

    private fun pauseCamera() {
        mQRReaderView.pause()
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun resumeCamera() {
        mQRReaderView.resume()
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationaleForCamera(request: PermissionRequest) {
        AlertDialog.Builder(this)
                .setPositiveButton("許可") {_, _ -> request.proceed() }
                .setNegativeButton("許可しない") {_, _ -> request.cancel()}
                .setMessage("カメラにアクセスする必要があります。")
                .show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onPermissionDenied() {
        finish()
    }
}
