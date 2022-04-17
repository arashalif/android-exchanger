package com.arshalif.paysera.view.exchange

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arshalif.paysera.databinding.ActivityExchangeBinding
import com.arshalif.paysera.view.model.ResultState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExchangeActivity : AppCompatActivity() {

    private val viewModel: ExchangeViewModel by viewModels()
    private var _binding: ActivityExchangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        observerBalance()
    }

    private fun initUI() {
        initSpinner()
        initBalanceList()
    }

    private fun initBalanceList() {
        binding.actExchangeRvBalance.apply {
            layoutManager =
                LinearLayoutManager(this@ExchangeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = BalanceAdapter(this@ExchangeActivity, viewModel.balances)
        }
    }

    private fun observerBalance() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.balancesState.collect {
                    when (it) {
                        is ResultState.Error -> {
                            binding.actExchangePbBalance.visibility = View.GONE
                            showError(it.message)
                        }
                        is ResultState.Loading -> binding.actExchangePbBalance.visibility =
                            View.VISIBLE
                        is ResultState.Success -> {
                            binding.actExchangePbBalance.visibility = View.GONE
                            binding.actExchangeRvBalance.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
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

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}