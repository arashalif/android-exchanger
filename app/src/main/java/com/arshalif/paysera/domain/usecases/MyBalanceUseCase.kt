package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.domain.repositories.BalanceRepository
import javax.inject.Inject

class MyBalanceUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository
) {

    suspend operator fun invoke() = balanceRepository.fetchBalances()
}