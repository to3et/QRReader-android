package com.example.to3et.qrreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class BarcodeResultFragment : Fragment() {
    private lateinit var viewModel: QRReaderViewModel
    private lateinit var barcodeResult: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            barcodeResult = it.getString(ARGS_BARCODE_RESULT) ?: ""
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(QRReaderViewModel::class.java)
        return inflater.inflate(R.layout.fragment_read_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.notifyBarcodeResult(barcodeResult)

        viewModel.barcodeResult.observe(this, Observer {
            val resultView = view?.findViewById<TextView>(R.id.barcode_result_view)
            resultView?.text = it
        })
    }

    companion object {
        private const val ARGS_BARCODE_RESULT = "barcode_result"

        @JvmStatic
        fun newInstance(barcodeResult: String) =
                BarcodeResultFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARGS_BARCODE_RESULT, barcodeResult)
                    }
                }
    }
}
