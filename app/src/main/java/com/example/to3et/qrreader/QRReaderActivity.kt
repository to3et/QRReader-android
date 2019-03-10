package com.example.to3et.qrreader

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import permissions.dispatcher.*

@RuntimePermissions
class QRReaderActivity : AppCompatActivity() {

    private lateinit var mQRReaderView: DecoratedBarcodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrreader)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        mQRReaderView = findViewById(R.id.decoratedBarcodeView)
        mQRReaderView.decodeSingle(object: BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result ?: return
//                mResultView.text = result.text
            }
            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        resumeCameraWithPermissionCheck()
    }

    override fun onPause() {
        super.onPause()
        stopCamera()
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun resumeCamera() {
        mQRReaderView.resume()
    }

    private fun stopCamera() {
        mQRReaderView.pause()
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
                .setMessage("QRを読み取るためにカメラにアクセスする必要があります。")
                .show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onPermissionDenied() {
        finish()
    }
}
