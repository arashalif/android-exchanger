package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.view.model.CurrencyState
import java.math.BigDecimal
import javax.inject.Inject


class CommissionUseCase @Inject constructor() {

    suspend operator fun invoke(
        valueSell: BigDecimal
    ): BigDecimal {
    }
}