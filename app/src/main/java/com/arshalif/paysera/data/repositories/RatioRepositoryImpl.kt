package com.arshalif.paysera.data.repositories

import com.arshalif.paysera.data.api.RatiosApi
import com.arshalif.paysera.data.api.model.RatioResponse
import com.arshalif.paysera.data.api.model.request.RatioRequest
import com.arshalif.paysera.domain.repositories.RatioRepository
import com.arshalif.paysera.view.model.ResultState
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class RatioRepositoryImpl @Inject constructor(private val ratiosApi: RatiosApi) : RatioRepository {

    override suspend fun getRatios(): Flow<ResultState<RatioResponse>> {
        return flow {
// TODO: needs while true and sleep for continuously request
            try {
                emit(
                    ResultState.Success(
                        ratiosApi.getRatio(
                            RatioRequest().getParamsMap()
                        )
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultState.Error(e.message ?: "Something went wrong!"))
            }
        }
    }
}