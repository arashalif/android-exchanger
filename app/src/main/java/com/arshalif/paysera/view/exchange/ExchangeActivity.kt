package com.arshalif.paysera.view.exchange

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arshalif.paysera.databinding.ActivityExchangeBinding

class ExchangeActivity : AppCompatActivity() {

    private val viewModel: ExchangeViewModel by viewModels()
    private var _binding: ActivityExchangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSpinner()
    }

    private fun initSpinner() {

        val provinceList = listOf(
            "EUR", "USD", "IRR", "TRK", "GPI", "LIR"
        )

        binding.actExchangeSpReceive.setSelection(0)
        binding.actExchangeSpSell.setSelection(0)
        binding.actExchangeSpReceive.item = provinceList
        binding.actExchangeSpSell.item = provinceList
        binding.actExchangeSpReceive.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {}
            }
    }
}