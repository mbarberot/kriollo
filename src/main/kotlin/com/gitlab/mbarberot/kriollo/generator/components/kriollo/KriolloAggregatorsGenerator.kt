package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KriolloAggregatorsGenerator(val serviceProvider: ServiceProvider) : Generator() {

    override fun execute(serviceProvider: ServiceProvider) {
        serviceProvider.configuration
            .components
            .kriollo
            .groupBy { moduleConfig -> moduleConfig.tag }
            .map { (tag, configurations) -> KriolloAggregatorGenerator(serviceProvider, tag, configurations) }
            .forEach { generator -> generator.execute(serviceProvider) }
    }
}