package features.utils

import com.gitlab.mbarberot.kriollo.utils.NamingUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NamingUtilsTest {

    @ParameterizedTest
    @CsvSource(
        value = [
            "kriollo, Kriollo",
            "Kriollo, Kriollo",
            "KRIOLLO, Kriollo",
            "kriollo-cli, KriolloCli",
            "kriollo_v1, KriolloV1",
            "kriollo-cli_v1, KriolloCliV1"
        ]
    )
    fun `convert a string to a class name`(value: String, expected: String) {
        // Arrange

        // Act
        val result = NamingUtils.toClassName(value)

        // Assert
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "kriollo, kriollo",
            "Kriollo, kriollo",
            "KRIOLLO, kriollo",
            "kriollo-cli, kriollo.cli",
            "kriollo_v1, kriollo.v1",
            "kriollo-cli_v1, kriollo.cli.v1"
        ]
    )
    fun `convert a string to a namespace`(value: String, expected: String) {
        // Arrange

        // Act
        val result = NamingUtils.toNamespace(value)

        // Assert
        assertThat(result).isEqualTo(expected)
    }
}

