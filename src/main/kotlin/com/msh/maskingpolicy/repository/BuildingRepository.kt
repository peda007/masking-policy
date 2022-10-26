package com.msh.maskingpolicy.repository

import com.msh.maskingpolicy.dto.GetBuildingResponseDto
import org.springframework.stereotype.Repository

@Repository
interface BuildingRepository {

    fun getBuildingList(): List<GetBuildingResponseDto>
}