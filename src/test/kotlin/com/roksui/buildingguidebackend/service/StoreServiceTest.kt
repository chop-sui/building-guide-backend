package com.roksui.buildingguidebackend.service

import com.roksui.buildingguidebackend.datasource.StoreDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class StoreServiceTest {

    private val dataSource: StoreDataSource = mockk(relaxed = true)
    private val storeService = StoreService(dataSource)

    @Test
    fun `should call its data source to retrieve stores`() {
        // when
        storeService.getStores()

        // then
        verify(exactly = 1) { dataSource.retrieveStores() }
    }
}