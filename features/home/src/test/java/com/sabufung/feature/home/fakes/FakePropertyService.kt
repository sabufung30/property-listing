package com.sabufung.feature.home.fakes

import com.sabufung.base.data.property.GetPropertiesResponse
import com.sabufung.base.data.api.PropertyService

class FakePropertyService: PropertyService {

    override suspend fun getProperties(): GetPropertiesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getRelatedItems(id: Long) {
        TODO("Not yet implemented")
    }
}