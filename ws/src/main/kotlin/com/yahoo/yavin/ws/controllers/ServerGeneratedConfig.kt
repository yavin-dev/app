/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */
package com.yahoo.yavin.ws.controllers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.yahoo.elide.spring.config.ElideConfigProperties
import com.yahoo.yavin.ws.config.UIConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.security.Principal

@Configuration
@Controller
class ServerGeneratedConfig @Autowired constructor(uiConfig: UIConfig, elideSettings: ElideConfigProperties) {
    private val mapper = ObjectMapper()
    private val uiSettings: UIConfig = uiConfig
    private val elideSettings: ElideConfigProperties = elideSettings

    @GetMapping(value = ["/ui/assets/server-generated-config.js"], produces = ["application/javascript"])
    @ResponseBody
    @Throws(JsonProcessingException::class)
    fun serverGeneratedConfig(user: Principal): String {
        uiSettings.user = user.name ?: ""
        return "var NAVI_APP = " + mapper.writeValueAsString(uiSettings) + ";"
    }
}
