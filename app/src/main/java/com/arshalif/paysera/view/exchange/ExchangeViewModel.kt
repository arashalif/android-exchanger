package com.arshalif.paysera.view.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.domain.model.Ratio
import com.arshalif.paysera.domain.usecases.MyBalanceUseCase
import com.arshalif.paysera.domain.usecases.RatioUseCase
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
    private val myBalanceUseCase: MyBalanceUseCase,
    private val ratioUseCase: RatioUseCase
) :
    ViewModel() {

    val balances = ArrayList<BalanceCurrency>()

    private val _balancesState: MutableStateFlow<ResultState<List<BalanceCurrency>>> =
        MutableStateFlow(ResultState.Loading)

    val balancesState: StateFlow<ResultState<List<BalanceCurrency>>>
        get() = _balancesState

    private val _ratiosState: MutableStateFlow<ResultState<HashMap<String, BigDecimal>>> =
        MutableStateFlow(ResultState.Loading)

    val ratiosState: StateFlow<ResultState<HashMap<String, BigDecimal>>>
        get() = _ratiosState

    init {
        fetchBalances()
        fetchRatio()
    }

    private fun fetchRatio() {
        viewModelScope.launch {
            ratioUseCase().collectLatest {
                when (val fetchedResult = it) {
                    is ResultState.Success -> {
                        _ratiosState.emit(ResultState.Success(fetchedResult.data))
                    }
                    is ResultState.Error -> {
                        _ratiosState.emit(fetchedResult)
                    }
                }
            }
        }
    }

    private fun fetchBalances() {
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
            }
        }
    }

}