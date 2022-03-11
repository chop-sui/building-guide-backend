package com.roksui.buildingguidebackend.service

import com.roksui.buildingguidebackend.datasource.StoreDataSource
import com.roksui.buildingguidebackend.model.Store
import org.springframework.stereotype.Service

@Service
class StoreService(private val dataSource: StoreDataSource) {

    fun getStores(): Collection<Store> = dataSource.retrieveStores()
    fun getStore(id: Int): Store = dataSource.retrieveStore(id)
    fun addStore(store: Store): Store = dataSource.createStore(store)

}