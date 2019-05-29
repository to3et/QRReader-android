package com.example.to3et.qrreader.barcoderesult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.to3et.qrreader.R
import com.example.to3et.qrreader.databinding.ActivityBarcoderesultBinding
import com.example.to3et.qrreader.model.SimpleBarcodeResult

class BarcodeResultActivity : AppCompatActivity() {

    private lateinit var viewModel: BarcodeResultViewModel

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityBarcoderesultBinding>(this, R.layout.activity_barcoderesult)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val barcodeResult = intent.getParcelableExtra<SimpleBarcodeResult>(EXTRA_BARCODE_RESULT)
        viewModel = ViewModelProviders.of(
                this,
                BarcodeResultViewModelFactory(barcodeResult))
                .get(BarcodeResultViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        setSupportActionBar(binding.toolbar)

        findOrCreateBarcodeResultFragment()
    }

    private fun findOrCreateBarcodeResultFragment() {
        var fragment = supportFragmentManager.findFragmentByTag(BarcodeResultFragment.TAG)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                    .replace(
                            R.id.contentFrame,
                            BarcodeResultFragment.newInstance(),
                            BarcodeResultFragment.TAG)
                    .commit()
        }
    }

    companion object {
        const val EXTRA_BARCODE_RESULT : String = "BARCODE_RESULT"

        fun intent(context: Context, barcodeResult: SimpleBarcodeResult): Intent =
                Intent(context, BarcodeResultActivity::class.java)
                        .putExtra(EXTRA_BARCODE_RESULT, barcodeResult)
    }
}
