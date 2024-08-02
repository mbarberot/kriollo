package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.services.provider.ServiceProvider

class KriolloAggregatorsGenerator(
    val configuration: CodegenConfiguration,
) : Generator() {

    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        configuration
            .components
            .kriollo
            .groupBy { moduleConfig -> moduleConfig.tag }
            .map { (tag, configurations) -> KriolloAggregatorGenerator(tag, configurations) }
            .forEach { generator -> generator.execute(configuration, serviceProvider) }
    }
}