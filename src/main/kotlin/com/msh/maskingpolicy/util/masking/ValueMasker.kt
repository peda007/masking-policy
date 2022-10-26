package com.msh.maskingpolicy.util.masking

import com.msh.maskingpolicy.util.masking.policy.MaskingPolicy
import java.lang.reflect.Field

class ValueMasker(
    private val maskingPolicy: MaskingPolicy
) {

    fun masking(target: Any) {
        if (!maskingPolicy.needMasking()) {
            return
        }
        val fields = target.javaClass.fields
        fields.forEach {
            it.isAccessible = true
            masking(it, target)
        }
    }

    private fun masking(it: Field, target: Any) {
        maskingString(it, target) || maskingStrings(it, target)
    }

    private fun maskingString(field: Field, target: Any): Boolean {
        val isString = field.type.isInstance(String::class)
        if (isString) {
            field.set(target, "***")
        }
        return isString
    }

    private fun maskingStrings(field: Field, target: Any): Boolean {
        val isStringList = field.type.isInstance(List::class)
                && field.genericType.javaClass.isInstance(String::class)
        if (isStringList) {
            val list = field.get(target) as List<*>
            list.forEach {
                checkNotNull(it)
                masking(it)
            }
        }
        return isStringList
    }
}