package com.csun.api.business

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.businessRoute() {

    val service = BusinessDataService()

    routing {
      get("api/{city}/{status}/{state}/{name}") {
          val city = call.parameters["city"] ?: "null";
          val status = call.parameters["status"] ?: "both"
          val state = call.parameters["state"] ?: "Any"
          val name = call.parameters["name"] ?: "null"
          println("City: $city")
          println("Status: $status");
          println("State: $state");
          println("Name: $name")
          val data = service.read(city, status, state, name)
          call.respond(HttpStatusCode.OK, mapOf("data" to data))
      }
    }
}

