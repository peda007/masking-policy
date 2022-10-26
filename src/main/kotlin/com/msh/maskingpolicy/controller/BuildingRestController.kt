package com.msh.maskingpolicy.controller

import com.msh.maskingpolicy.dto.GetBuildingResponseDto
import com.msh.maskingpolicy.service.BuildingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/buildings")
class BuildingRestController(
    private val buildingService: BuildingService
) {

    @GetMapping
    fun getBuildingList(): ResponseEntity<List<GetBuildingResponseDto>> {
        return ResponseEntity.ok(buildingService.getBuildings())
    }
}