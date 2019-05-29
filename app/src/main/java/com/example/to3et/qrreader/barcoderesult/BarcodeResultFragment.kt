package com.example.to3et.qrreader.barcoderesult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.to3et.qrreader.R
import com.example.to3et.qrreader.databinding.FragmentReadViewBinding
import com.example.to3et.qrreader.util.ClipBoardUtils
import com.example.to3et.qrreader.util.SnackbarUtils

class BarcodeResultFragment : Fragment() {
    private lateinit var viewModel: BarcodeResultViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(requireActivity()).get(BarcodeResultViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentReadViewBinding>(inflater,
                R.layout.fragment_read_view,
                container,
                false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<AppCompatButton>(R.id.button1)
        button.setOnClickListener { viewModel.copyText()}

        setupClipBoard()
        setupSnackbarText()
    }

    private fun setupClipBoard() {
        viewModel.copyText.observe(this, Observer<String> {text ->
            ClipBoardUtils.copyText(requireActivity(), text)
        })
    }

    private fun setupSnackbarText() {
        viewModel.snackbarText.observe(this, Observer<String> {text ->
            view?.let {
                SnackbarUtils.showSnackbar(it, text)
            }
        })
    }

    companion object {
        const val TAG = "BarcodeResultFragment"

        @JvmStatic
        fun newInstance() = BarcodeResultFragment()
    }
}