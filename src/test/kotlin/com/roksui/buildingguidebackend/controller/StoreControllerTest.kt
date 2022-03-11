package com.roksui.buildingguidebackend.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class StoreControllerTest {
    
    @Autowired
    lateinit var mockMvc: MockMvc

    val baseUrl = "/api/stores"

    @Nested
    @DisplayName("getStores()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetStores {
        @Test
        fun `should return all stores`() {
            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].name") { value("KFC") }
                }
        }
    }

    @Nested
    @DisplayName("getStore()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetStore {
        @Test
        fun `should return the store with given id`() {
            // given
            val id = 1

            // when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.name") { value("KFC") }
                }
        }

        @Test
        fun `should return NOT FOUND if the id does not exist`() {
            // given
            val id = -1

            // when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
    

}