package com.gitlab.mbarberot.kriollo.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SemVerTest {

    @ParameterizedTest(name = "{0} is newer or equal to 1.2.3")
    @CsvSource(
        "0.2.3,false", // older by major
        "1.1.3,false", // older by minor
        "1.2.2,false", // older by patch
        "1.2.3,true", // equal
        "1.2.4,true", // newer by path
        "1.3.3,true", // newer by minor
        "2.2.3,true", // newer by major
    )
    fun `test newer or equal version`(actualVersion: String, expected: Boolean) {
        assertThat(SemVer(actualVersion).isNewerOrEqualTo("1.2.3")).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{0} is newer to 1.2.3")
    @CsvSource(
        "0.2.3,false", // older by major
        "1.1.3,false", // older by minor
        "1.2.2,false", // older by patch
        "1.2.3,false", // equal
        "1.2.4,true", // newer by path
        "1.3.3,true", // newer by minor
        "2.2.3,true", // newer by major
    )
    fun `test strictly newer version`(actualVersion: String, expected:Boolean) {
        assertThat(SemVer(actualVersion).isNewerTo("1.2.3")).isEqualTo(expected)
    }

}