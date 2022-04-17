package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.domain.repositories.RatioRepository
import javax.inject.Inject

class RatioUseCase @Inject constructor(
    private val ratioRepository: RatioRepository
) {
    suspend operator fun invoke() = ratioRepository.getRatios()

}