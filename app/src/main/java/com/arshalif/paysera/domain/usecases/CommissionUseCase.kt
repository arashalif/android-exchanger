package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.domain.repositories.BalanceRepository
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject


class CommissionUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository
) {

    suspend operator fun invoke(
        valueSell: BigDecimal
    ): BigDecimal {
        val countTransAction = balanceRepository.fetchNumberOfTransactions()
        return if (countTransAction <= NUMBER_OF_FREE_TRANSACTION) {
            BigDecimal.valueOf(0.0)
        } else {
            (valueSell * BigDecimal.valueOf(NUMBER_OF_COMMISSION)).setScale(2, RoundingMode.UP)
        }
    }

    companion object {
        const val NUMBER_OF_FREE_TRANSACTION = 5
        const val NUMBER_OF_COMMISSION = 0.007
    }
}