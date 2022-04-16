package com.arshalif.paysera.view.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshalif.paysera.data.db.Balance
import com.arshalif.paysera.domain.repositories.BalanceRepository
import com.arshalif.paysera.view.model.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(private val balanceRepository: BalanceRepository) :
    ViewModel() {

    val balances = ArrayList<Balance>()
    private val _balancesState: MutableStateFlow<ResultState<List<Balance>>> =
        MutableStateFlow(ResultState.Loading)

    val balancesState: StateFlow<ResultState<List<Balance>>>
        get() = _balancesState

    fun fetchBalances() {
        viewModelScope.launch {
            _balancesState.emit(ResultState.Loading)
            when (val fetchedResult = balanceRepository.fetchBalances()) {
                is ResultState.Success -> {

                }
                is ResultState.Error -> {
                    _balancesState.emit(fetchedResult)
                }
            }
        }
    }

}