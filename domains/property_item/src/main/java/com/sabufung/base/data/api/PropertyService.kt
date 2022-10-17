package com.sabufung.base.data.api

import com.sabufung.base.data.property.GetPropertiesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PropertyService {
    @GET("properties")
    suspend fun getProperties(): GetPropertiesResponse

    @GET("properties/{id}")
    suspend fun getRelatedItems(
        @Path("id") id: Long,
    )
}