package com.csun.api.business

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.slf4j.LoggerFactory

class BusinessDataService {

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun read(city: String, status: String, state: String, name: String): List<ExposedBusinessData> {
        return dbQuery {
            var query = BusinessTable.selectAll()

            if(city != "null") {
                city.let {
                    query = query.andWhere { BusinessTable.city eq it }
                }
            }

            if (status != "both") {
                val bit = if (status.equals("Open", ignoreCase = true)) 1 else 0;
                query = query.andWhere { BusinessTable.isOpen eq bit }
            }

            if(state != "null") {
                state.let {
                    query = query.andWhere { BusinessTable.state eq it }
                }
            }

            if(name != "null") {
                name.let {
                    query = query.andWhere { BusinessTable.name eq it }
                }
            }

            query.limit(100)
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