package com.roksui.buildingguidebackend.datasource

import com.roksui.buildingguidebackend.model.Store

interface StoreDataSource {

    fun retrieveStores(): Collection<Store>

    fun retrieveStore(storeId: Int): Store

    fun createStore(store: Store): Store

    fun updateStore(store: Store): Store

    fun deleteStore(storeId: Int)
}