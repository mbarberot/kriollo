package com.gitlab.mbarberot.kriollo.utils


class SemVer(val version: String) {

    companion object {
        private val VERSION_REGEX = Regex("""^(\d+)\.(\d+)\.(\d+)$""")
    }

    private val major: Int
    private val minor: Int
    private val patch: Int

    init {
        require(version.matches(VERSION_REGEX)) { "Version must be in format MAJOR.MINOR.PATCH" }
        val matchResult = VERSION_REGEX.find(version)!!
        major = matchResult.groupValues[1].toInt()
        minor = matchResult.groupValues[2].toInt()
        patch = matchResult.groupValues[3].toInt()
    }

    fun isNewerOrEqualTo(otherVersion: String): Boolean {
        val other = SemVer(otherVersion)

        if (isEqualTo(other)) {
            return true
        }

        return when {
            major > other.major -> true
            minor > other.minor -> true
            patch > other.patch -> true
            else -> false
        }
    }

    fun isNewerTo(otherVersion: String): Boolean {
        val other = SemVer(otherVersion)

        if (isEqualTo(other)) {
            return false
        }

        return when {
            major > other.major -> true
            minor > other.minor -> true
            patch > other.patch -> true
            else -> false
        }
    }

    fun isEqualTo(otherVersion: String): Boolean {
        val other = SemVer(otherVersion)
        return isEqualTo(other)
    }

    fun isEqualTo(other: SemVer): Boolean {
        return major == other.major
                && minor == other.minor
                && patch == other.patch

    }

}

