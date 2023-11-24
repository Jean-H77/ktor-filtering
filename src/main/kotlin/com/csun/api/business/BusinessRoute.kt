package com.csun.api.business

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.businessRoute() {

    val service = BusinessDataService();

    routing {
        get("/data/{city}") {
            val city = call.parameters["city"] ?: throw IllegalArgumentException("Invalid parameter(s)")
            val data = service.read(city)
            if (data != null) {
                call.respond(HttpStatusCode.OK, mapOf("data" to data))
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Data not found"))
            }
        }
    }
}

