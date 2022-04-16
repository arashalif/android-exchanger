package com.arshalif.paysera.view.exchange

import androidx.lifecycle.ViewModel
import com.arshalif.paysera.domain.repositories.BalanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(private val balanceRepository: BalanceRepository) :
    ViewModel() {
}