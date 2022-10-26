package com.msh.maskingpolicy.service

import com.msh.maskingpolicy.dto.GetBuildingResponseDto
import com.msh.maskingpolicy.repository.BuildingRepository
import com.msh.maskingpolicy.util.masking.ValueMasker
import com.msh.maskingpolicy.util.masking.policy.MultipleMaskingPolicy
import com.msh.maskingpolicy.util.masking.policy.ProgressingDealMaskingPolicy
import com.msh.maskingpolicy.util.masking.policy.UnauthorizedUserMaskingPolicy
import org.springframework.stereotype.Service

@Service
class BuildingService(
    private val buildingRepository: BuildingRepository
) {

    fun getBuildings(): List<GetBuildingResponseDto> {
        val buildingList = buildingRepository.getBuildingList()

        val valueMasker = ValueMasker(MultipleMaskingPolicy(
            UnauthorizedUserMaskingPolicy(),
            ProgressingDealMaskingPolicy()
        ))
        valueMasker.masking(buildingList)

        return buildingList
    }
}