package com.arshalif.paysera.data.repositories

import com.arshalif.paysera.data.api.RatiosApi
import com.arshalif.paysera.domain.repositories.RatioRepository
import com.arshalif.paysera.view.model.ResultState
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import javax.inject.Inject

@ActivityRetainedScoped
class RatioRepositoryImpl @Inject constructor(ratiosApi: RatiosApi) : RatioRepository {

    override suspend fun getRatios(): Flow<ResultState<HashMap<String, BigDecimal>>> {
        TODO("Not yet implemented")
    }

}