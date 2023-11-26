package com.csun.api.business

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
