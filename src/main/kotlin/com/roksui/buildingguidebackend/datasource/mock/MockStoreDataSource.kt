package com.roksui.buildingguidebackend.datasource.mock

import com.roksui.buildingguidebackend.datasource.StoreDataSource
import com.roksui.buildingguidebackend.model.Store
import org.springframework.stereotype.Repository

@Repository
class MockStoreDataSource: StoreDataSource {

    val stores = mutableListOf(Store(1, "KFC"), Store(2, "Dior"))

    override fun retrieveStores(): Collection<Store> = stores

    override fun retrieveStore(id: Int): Store = stores.first { it.id == id }

    override fun createStore(store: Store): Store {
        if (stores.any { it.name == store.name }) {
            throw IllegalArgumentException("Store with name ${store.name} already exists.")
        }
        stores.add(store)
        return store
    }

    override fun updateStore(store: Store): Store {
        TODO("Not yet implemented")
    }

    override fun deleteStore(storeId: Int) {
        TODO("Not yet implemented")
    }
}