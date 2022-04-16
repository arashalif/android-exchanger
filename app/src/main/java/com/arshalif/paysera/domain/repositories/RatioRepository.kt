package com.arshalif.paysera.domain.repositories

import com.arshalif.paysera.view.model.ResultState
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface RatioRepository {

    suspend fun getRatios(): Flow<ResultState<HashMap<String, BigDecimal>>>

}