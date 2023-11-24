package com.csun

import com.csun.api.business.businessRoute
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import org.jetbrains.exposed.sql.Database

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader("key")
        anyHost()
    }
    install(ContentNegotiation) {
        json()
    }
    Database.connect(
        url = "jdbc:mysql://localhost:13313/assign",
        user = "root",
        driver = "com.mysql.cj.jdbc.Driver",
        password = "example"
    )

    businessRoute()
}
