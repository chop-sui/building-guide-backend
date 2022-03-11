package com.roksui.buildingguidebackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.roksui.buildingguidebackend.model.Store
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
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class StoreControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/stores"

    @Nested
    @DisplayName("GET /api/stores")
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
    @DisplayName("GET /api/store/{id}")
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

    @Nested
    @DisplayName("POST /api/stores")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewStore {
        
        @Test
        fun `should add the new store`() {
            // given
            val newStore = Store(10, "McDonald's")
            
            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newStore)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") { value(10) }
                    jsonPath("$.name") { value("McDonald's") }
                }
        }

        @Test
        fun `should return BAD REQUEST if store with given name already exists`() {
            // given
            val invalidStore = Store(11, "KFC")

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidStore)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
        }
    }
}