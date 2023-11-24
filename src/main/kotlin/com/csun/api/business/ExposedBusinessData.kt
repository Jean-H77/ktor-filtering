package com.csun.api.business

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
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

@Serializer(forClass = BigDecimal::class)
object BigDecimalSerializer : KSerializer<BigDecimal> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): BigDecimal {
        return BigDecimal(decoder.decodeString())
    }
}
