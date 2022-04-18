package com.arshalif.paysera.view.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExchangeActivity : AppCompatActivity() {

    private val viewModel: ExchangeViewModel by viewModels()
    private var _binding: ActivityExchangeBinding? = null
    private val binding get() = _binding!!

    private val textWatch = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (viewModel.exchangeState.value != null) {
                viewModel.updateExchange(
                    binding.actExchangeSpSell.selectedItem.toString(),
                    s.toString(),
                    binding.actExchangeSpReceive.selectedItem.toString()
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        observer()
    }

    private fun initUI() {
        initBalanceList()
        initSubmit()
    }

    private fun initSubmit() {
        binding.actExchangeTxtSubmit.setOnClickListener {
            viewModel.submitExchange(
                binding.actExchangeSpSell.selectedItem.toString(),
                binding.actExchangeEtExchange.text.toString(),
                binding.actExchangeSpReceive.selectedItem.toString(),
                binding.actExchangeTxtExchanged.text.toString()
            )
        }
    }

    private fun initBalanceList() {
        binding.actExchangeRvBalance.apply {
            layoutManager =
                LinearLayoutManager(
                    this@ExchangeActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = BalanceAdapter(this@ExchangeActivity, viewModel.balances)
        }
    }

    private fun updateSpinner(provinceList: List<String>) {
        binding.actExchangeSpReceive.item = provinceList
        binding.actExchangeSpSell.item = provinceList
        binding.actExchangeSpSell.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    viewModel.updateExchange(
                        provinceList[position],
                        binding.actExchangeEtExchange.text.toString(),
                        binding.actExchangeSpReceive.selectedItem.toString()
                    )
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {}
            }
        binding.actExchangeSpReceive.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    viewModel.updateExchange(
                        binding.actExchangeSpSell.selectedItem.toString(),
                        binding.actExchangeEtExchange.text.toString(),
                        provinceList[position]
                    )
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {}
            }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun observer() {
        observerBalance()
        observerRatio()
        observerExchange()
    }

    private fun observerExchange() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.exchangeState.collectLatest {
                    binding.actExchangeEtExchange.removeTextChangedListener(textWatch)

                    it ?: return@collectLatest
                    binding.actExchangeSpSell.setSelection(
                        viewModel.ratios.keys.toList().indexOfFirst { temp ->
                            temp == it.first.type
                        })
                    binding.actExchangeEtExchange.setText(it.first.value.toString())
                    binding.actExchangeSpReceive.setSelection(
                        viewModel.ratios.keys.toList().indexOfFirst { temp ->
                            temp == it.second.type
                        })
                    binding.actExchangeTxtExchanged.text = it.second.value.toString()

                    binding.actExchangeEtExchange.addTextChangedListener(textWatch)
                }
            }
        }
    }

    private fun observerRatio() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.ratiosState.collect {
                    when (it) {
                        is ResultState.Error -> {
                            showError(it.message)
                        }
                        is ResultState.Loading -> {}
                        is ResultState.Success -> {
                            updateSpinner(it.data.keys.toList())
                            viewModel.initExchange()
                        }
                    }
                }
            }
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
                            viewModel.initExchange()
                        }
                    }
                }
            }
        }
    }

}