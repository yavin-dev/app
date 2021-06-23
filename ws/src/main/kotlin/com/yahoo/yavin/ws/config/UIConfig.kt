/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */
package com.yahoo.yavin.ws.config

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.yahoo.elide.spring.config.ElideConfigProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "ui")
@JsonSerialize(`as` = UIConfig::class)
class UIConfig @Autowired constructor(elideSettings: ElideConfigProperties) {
    var user = ""

    var dataSources: List<DataSource> = listOf(DataSource("default", "Media", "Info about movies and TV shows.", elideSettings.graphql.path, DataSourceTypes.elide))

    var appPersistence = DataSource("persistence", "Persistence", null, elideSettings.jsonApi.path, DataSourceTypes.elide)

    @JsonProperty("FEATURES")
    var features = UIFeatureSettings()
}

class UIFeatureSettings {
    var enableDashboardsFilters = true
    var enableTableEditing = true
    var enableTotals = true
}
