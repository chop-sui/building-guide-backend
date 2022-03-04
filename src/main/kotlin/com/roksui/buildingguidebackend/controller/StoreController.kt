package com.roksui.buildingguidebackend.controller

import com.roksui.buildingguidebackend.model.Store
import com.roksui.buildingguidebackend.service.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stores")
class StoreController(private val service: StoreService) {

    @GetMapping
    fun getStores(): Collection<Store> = service.getStores()
}