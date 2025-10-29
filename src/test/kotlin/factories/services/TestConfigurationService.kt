package factories.services

import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationService
import kotlin.reflect.KClass

class TestConfigurationService: ConfigurationService {

    val configs: MutableMap<String, Any> = mutableMapOf()

    init {
        // default configs
        withTestConfig(KotlinConfiguration(enabled = false))
    }

    override fun <T : Any> get(klass: KClass<T>): T {
        val key = klass.simpleName ?: throw RuntimeException("Cannot get simple name of class $klass")

        @Suppress("UNCHECKED_CAST") // this should be handled by the tester
        return configs[key] as T
    }

    fun withTestConfig(config: Any): TestConfigurationService {
        val key = config::class.simpleName ?: throw RuntimeException("Cannot get simple name of class ${config::class}")
        configs[key] = config
        return this
    }
}