package kriollo.generator.components.kriollo

import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider

class KriolloAggregatorsGenerator(val serviceProvider: ServiceProvider) : Generator() {

    override fun execute(serviceProvider: ServiceProvider) {
        serviceProvider.configuration
            .components
            .kriollo
            .groupBy { moduleConfig -> moduleConfig.tag }
            .map { (tag, configurations) -> KriolloAggregatorGenerator(tag, configurations) }
            .forEach { generator -> generator.execute(serviceProvider) }
    }
}