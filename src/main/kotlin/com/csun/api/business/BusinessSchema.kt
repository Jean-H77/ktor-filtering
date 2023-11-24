package com.csun.api.business

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable

object BusinessTable : IdTable<String>("business") {
    override val id = varchar("business_id", 1024).entityId()

    val name = BusinessTable.varchar("name", 1024)
    val address = BusinessTable.varchar("address", 1024)
    val city = varchar("city", 1024);
    val state = BusinessTable.varchar("state", 1024)
    val postalCode = BusinessTable.varchar("postal_code", 1024)
    val stars = BusinessTable.decimal("stars", 8, 2)
    val reviewCount = BusinessTable.integer("review_count")
    val isOpen = BusinessTable.integer("is_open");
    val categories = BusinessTable.varchar("categories", 1024)
}

class Business(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, Business>(BusinessTable)

    var name by BusinessTable.name
    var address by BusinessTable.address
    var city by BusinessTable.city
    var state by BusinessTable.state
    var postalCode by BusinessTable.postalCode
    var stars by BusinessTable.stars
    var reviewCount by BusinessTable.reviewCount
    var isOpen by BusinessTable.isOpen
    var categories by BusinessTable.categories
}
