package com.csun.api.business

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.businessRoute() {

    val service = BusinessDataService()

    routing {
      get("api/{city}/{status}/{state}/{name}/{pageNumber}") {
          val city = call.parameters["city"] ?: "null";
          val status = call.parameters["status"] ?: "both"
          val state = call.parameters["state"] ?: "Any"
          val name = call.parameters["name"] ?: "null"
          val pageNumber = call.parameters["pageNumber"] ?: "0"
          println("City: $city")
          println("Status: $status");
          println("State: $state");
          println("Name: $name")
          println("pageNumber: $pageNumber")
          val data = service.read(city, status, state, name, Integer.parseInt(pageNumber))
          call.respond(HttpStatusCode.OK, mapOf("data" to data))
      }
    }
}

