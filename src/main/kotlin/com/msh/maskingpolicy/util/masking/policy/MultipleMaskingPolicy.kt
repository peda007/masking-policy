package com.msh.maskingpolicy.util.masking.policy

class MultipleMaskingPolicy(
    private vararg val maskingPolicies: MaskingPolicy
) : MaskingPolicy {
    override fun needMasking(): Boolean {
        return maskingPolicies
            .map { it.needMasking() }
            .reduce { result, needMasking -> result && needMasking }
    }
}