/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */
package com.yahoo.yavin.ws

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan(basePackages = ["com.yahoo.navi.ws.models", "com.yahoo.yavin.ws.models"])
@EnableJpaRepositories
class App {
    private val LOGGER: Logger = LoggerFactory.getLogger(App::class.java)

    @Value("\${server.port}")
    private val serverPort = 0

    @EventListener(ApplicationReadyEvent::class)
    fun startApp(event: ApplicationReadyEvent) {
        LOGGER.info("""
            
        =========================================================
                 
          Yavin App Serving On Port: $serverPort (e.g. localhost:$serverPort)
         
        =========================================================
        """.trimIndent())
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}
