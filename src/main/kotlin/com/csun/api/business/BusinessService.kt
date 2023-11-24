package com.csun.api.business

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class BusinessDataService {

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun read(city: String): ExposedBusinessData? {
        return dbQuery {
            Business.find { BusinessTable.city eq city }
                .singleOrNull()
                ?.let { business ->
                    ExposedBusinessData(
                        business.name,
                        business.address,
                        business.city,
                        business.state,
                        business.postalCode,
                        business.stars,
                        business.reviewCount,
                        business.isOpen,
                        business.categories
                    )
                }
        }
    }
}