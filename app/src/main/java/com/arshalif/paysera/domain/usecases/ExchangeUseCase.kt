package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.domain.repositories.ExchangeRepository
import javax.inject.Inject

class ExchangeUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    private val balanceExchangeRepository: ExchangeRepository
) {

    suspend operator fun invoke() = {
    }
}