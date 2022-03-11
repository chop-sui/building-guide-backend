package com.roksui.buildingguidebackend.controller

import com.roksui.buildingguidebackend.model.Store
import com.roksui.buildingguidebackend.service.StoreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stores")
class StoreController(private val service: StoreService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getStores(): Collection<Store> = service.getStores()

    @GetMapping("/{id}")
    fun getStore(@PathVariable id: Int) = service.getStore(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addStore(@RequestBody store: Store): Store = service.addStore(store)
}