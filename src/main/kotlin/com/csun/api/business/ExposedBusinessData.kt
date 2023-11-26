package com.csun.api.business

import com.csun.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ExposedBusinessData(
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val postalCode: String,
    @Serializable(with = BigDecimalSerializer::class)
    val stars: BigDecimal,
    val reviewCount: Int,
    val isOpen: Int,
    val categories: String
)