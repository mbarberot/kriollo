package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.generator.git.GitIgnoreExtension
import com.gitlab.mbarberot.kriollo.generator.java.JavaGitIgnoreExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KotlinGitIgnoreExtension(serviceProvider: ServiceProvider) :
    JavaGitIgnoreExtension(serviceProvider), GitIgnoreExtension
