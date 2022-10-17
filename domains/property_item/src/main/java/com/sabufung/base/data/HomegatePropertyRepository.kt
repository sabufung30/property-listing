package com.sabufung.base.data

import android.util.Log
import androidx.datastore.core.DataStore
import com.sabufung.base.di.data.UserPreferences
import com.sabufung.base.data.api.PropertyService
import com.sabufung.base.data.db.daos.PropertyDao
import com.sabufung.base.data.db.model.toDomain
import com.sabufung.base.data.property.toEntity
import com.sabufung.base.data.repositories.PropertyRepository
import com.sabufung.domain.model.property.Property
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomegatePropertyRepository @Inject constructor(
    private val propertyService: PropertyService,
    private val propertyDao: PropertyDao,
    private val userDataStore: DataStore<UserPreferences>
) : PropertyRepository {

    override val bookmarkStream: Flow<Set<String>> = userDataStore.data.map { it.bookmarkedPropertyIdsMap.keys }

    override suspend fun getListingProperty(): Flow<List<Property>> {
        //TODO: replace try-catch block with more elegant solution
        try {
            val result = propertyService.getProperties().results
            if (result.isNotEmpty()) {
                propertyDao.insertAll(result.map { it.toEntity() })
            }
        } catch (e:Exception) {
            e.message?.let { Log.e("Network Error", it) }
        }

        return propertyDao.getAllProperties().map { it.map { propertyEntity -> propertyEntity.toDomain() } }
    }

    override suspend fun toggleBookmark(id: String, value: Boolean) {
        userDataStore.updateData {
            if (value) {
                it.toBuilder().putBookmarkedPropertyIds(id,true).build()
            } else {
                it.toBuilder().removeBookmarkedPropertyIds(id).build()
            }

        }
    }
}

