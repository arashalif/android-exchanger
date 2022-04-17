package com.arshalif.paysera.view.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.domain.usecases.MyBalanceUseCase
import com.arshalif.paysera.view.model.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(private val myBalanceUseCase: MyBalanceUseCase) :
    ViewModel() {

    val balances = ArrayList<BalanceCurrency>()

    private val _balancesState: MutableStateFlow<ResultState<List<BalanceCurrency>>> =
        MutableStateFlow(ResultState.Loading)

    val balancesState: StateFlow<ResultState<List<BalanceCurrency>>>
        get() = _balancesState

    init {
        fetchBalances()
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
            }
        }
    }

}