package com.arshalif.paysera.data.repositories

import com.arshalif.paysera.data.api.ApiConstants
import com.arshalif.paysera.data.api.RatiosApi
import com.arshalif.paysera.data.api.model.request.RatioRequest
import com.arshalif.paysera.domain.repositories.RatioRepository
import com.arshalif.paysera.view.model.ResultState
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import javax.inject.Inject

@ActivityRetainedScoped
class RatioRepositoryImpl @Inject constructor(private val ratiosApi: RatiosApi) : RatioRepository {

    override suspend fun getRatios(): Flow<ResultState<HashMap<String, BigDecimal>>> {
        return flow {
            while (true) {
                try {
                    val response = ratiosApi.getRatio(
                        RatioRequest().getParamsMap()
                    )

                    val ratios = hashMapOf<String, BigDecimal>()
                    ratios[response.base] = BigDecimal(1.0)
                    ratios.putAll(response.rates)
                    emit(ResultState.Success(ratios))

                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(ResultState.Error(e.message ?: "Something went wrong!"))
                } finally {
                    delay(ApiConstants.INTERVAL_RATIO)
                }
            }
        }
    }
}