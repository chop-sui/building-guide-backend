package com.roksui.buildingguidebackend.datasource.mock

import com.roksui.buildingguidebackend.datasource.StoreDataSource
import com.roksui.buildingguidebackend.model.Store
import org.springframework.stereotype.Repository

@Repository
class MockStoreDataSource: StoreDataSource {

    val stores = listOf(Store("KFC"), Store("Dior"))

    override fun retrieveStores(): Collection<Store> = stores

    override fun retrieveStore(storeId: Int): Store {
        TODO("Not yet implemented")
    }

    override fun createStore(store: Store): Store {
        TODO("Not yet implemented")
    }

    override fun updateStore(store: Store): Store {
        TODO("Not yet implemented")
    }

    override fun deleteStore(storeId: Int) {
        TODO("Not yet implemented")
    }
}