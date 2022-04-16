package com.arshalif.paysera.data.api

import com.arshalif.paysera.data.api.model.RatioResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RatiosApi {

    @GET(ApiConstants.LATEST_API)
    suspend fun getRatio(
        @QueryMap payload: HashMap<String, String>
    ): Response<RatioResponse>
}