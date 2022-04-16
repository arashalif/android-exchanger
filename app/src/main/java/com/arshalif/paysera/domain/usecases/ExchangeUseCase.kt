package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.domain.repositories.RatioRepository
import javax.inject.Inject

class ExchangeUseCase @Inject constructor(
    private val exchangeRepository: RatioRepository,
    private val balanceExchangeRepository: RatioRepository
) {

    suspend operator fun invoke() = {
    }
}