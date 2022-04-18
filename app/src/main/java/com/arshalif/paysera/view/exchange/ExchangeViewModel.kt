package com.arshalif.paysera.view.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.domain.usecases.ExchangeUseCase
import com.arshalif.paysera.domain.usecases.MyBalancesUseCase
import com.arshalif.paysera.domain.usecases.RatioUseCase
import com.arshalif.paysera.domain.usecases.SubmitExchangeUseCase
import com.arshalif.paysera.view.model.CurrencyRatioState
import com.arshalif.paysera.view.model.CurrencyState
import com.arshalif.paysera.view.model.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val myBalanceUseCase: MyBalancesUseCase,
    private val ratioUseCase: RatioUseCase,
    private val exchangeUseCase: ExchangeUseCase,
    private val submitExchangeUseCase: SubmitExchangeUseCase
) :
    ViewModel() {

    val balances = ArrayList<BalanceCurrency>()
    val ratios = HashMap<String, BigDecimal>()

    private val _balancesState: MutableStateFlow<ResultState<List<BalanceCurrency>>> =
        MutableStateFlow(ResultState.Loading)

    val balancesState: StateFlow<ResultState<List<BalanceCurrency>>>
        get() = _balancesState

    private val _ratiosState: MutableStateFlow<ResultState<HashMap<String, BigDecimal>>> =
        MutableStateFlow(ResultState.Loading)

    val ratiosState: StateFlow<ResultState<HashMap<String, BigDecimal>>>
        get() = _ratiosState

    var exchangeState: MutableStateFlow<Pair<CurrencyState, CurrencyState>?> =
        MutableStateFlow(null)

    var submitState: MutableStateFlow<ResultState<String>> =
        MutableStateFlow(ResultState.Loading)

    init {
        fetchBalances()
        fetchRatio()
    }

    private fun fetchRatio() {
        viewModelScope.launch {
            ratioUseCase().collectLatest {
                when (val fetchedResult = it) {
                    is ResultState.Success -> {
                        ratios.putAll(fetchedResult.data)
                        _ratiosState.emit(ResultState.Success(ratios))

                    }
                    is ResultState.Error -> {
                        _ratiosState.emit(fetchedResult)
                    }
                    ResultState.Loading -> {}
                }
            }
        }
    }

     fun fetchBalances() {
        viewModelScope.launch {
            _balancesState.emit(ResultState.Loading)
            when (val fetchedResult = myBalanceUseCase()) {
                is ResultState.Success -> {
                    balances.clear()
                    balances.addAll(fetchedResult.data)
                    _balancesState.emit(ResultState.Success(balances))
                }
                is ResultState.Error -> {
                    _balancesState.emit(fetchedResult)
                }
                ResultState.Loading -> {}
            }
        }
    }

    fun initExchange() {
        if (ratios.isEmpty().not() && balances.isEmpty().not() && exchangeState.value == null) {
            viewModelScope.launch {
                exchangeState.emit(
                    Pair(
                        CurrencyState(balances.first().type, BigDecimal.valueOf(1.0)),
                        CurrencyState(balances.first().type, BigDecimal.valueOf(1.0))
                    )
                )
            }
        }
    }

    fun updateExchange(typeSell: String, valueSell: String, typeReceive: String) {
        viewModelScope.launch {
            val sell = CurrencyState(typeSell, BigDecimal.valueOf(valueSell.toDouble()))
            exchangeState.emit(
                Pair(
                    CurrencyState(typeSell, BigDecimal.valueOf(valueSell.toDouble())),
                    exchangeUseCase(
                        CurrencyRatioState(sell, ratios[typeSell]!!),
                        typeReceive, ratios[typeReceive]!!
                    )
                )
            )
        }
    }

    fun submitExchange(
        typeSell: String,
        valueSell: String,
        typeReceive: String,
        valueReceive: String
    ) {
        viewModelScope.launch {
            submitState.emit(submitExchangeUseCase(typeSell, valueSell, typeReceive, valueReceive))
        }
    }
}