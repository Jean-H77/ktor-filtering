package com.csun.api.business

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class BusinessDataService {

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun read(city: String): List<ExposedBusinessData> {
        return dbQuery {
            BusinessTable.select { BusinessTable.city eq city }
                .limit(100)
                .map {
                    ExposedBusinessData(
                        it[BusinessTable.name],
                        it[BusinessTable.address],
                        it[BusinessTable.city],
                        it[BusinessTable.state],
                        it[BusinessTable.postalCode],
                        it[BusinessTable.stars],
                        it[BusinessTable.reviewCount],
                        it[BusinessTable.isOpen],
                        it[BusinessTable.categories]
                    )
                }.toList();
        }
    }
}