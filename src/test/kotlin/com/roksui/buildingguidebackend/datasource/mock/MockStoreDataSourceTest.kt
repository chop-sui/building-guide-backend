package com.roksui.buildingguidebackend.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockStoreDataSourceTest {

    private val mockDataSource = MockStoreDataSource()

    @Test
    fun `should provide a collection of stores`() {
        // when
        val stores = mockDataSource.retrieveStores()

        // then
        assertThat(stores).isNotEmpty
    }

    @Test
    fun `should provide some mock data`() {
        // when
        val stores = mockDataSource.retrieveStores()

        // then
        assertThat(stores).allMatch { it.name.isNotBlank() }
    }
}