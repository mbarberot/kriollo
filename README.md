# Kriollo

[![pipeline status](https://gitlab.com/mbarberot/kriollo/badges/main/pipeline.svg)](https://gitlab.com/mbarberot/kriollo/-/commits/main)
[![coverage report](https://gitlab.com/mbarberot/kriollo/badges/main/coverage.svg)](https://gitlab.com/mbarberot/kriollo/-/commits/main)
[![Latest Release](https://gitlab.com/mbarberot/kriollo/-/badges/release.svg)](https://gitlab.com/mbarberot/kriollo/-/releases)

## The whole configuration

If not marked as `required`, then the default value is shown and there is no need to fill the
associated configuration.

```kdl
// Kriollo configuration
kriollo {
    dir "codegen" // Directory containing the codegen.kdl and kriollo.jar
}

// Main configuration
project {
    groupId "__GROUP_ID__" // required
    name "__NAME__" // required
    version "__VERSION__" // required
    mainClass "__MAIN_CLASS_FILE__" // required, relative to project namespace
    
    // Note about the project namespace and path
    // The project namespace is generated from the groupId and name
    // namespace = $groupId.$name
    // Example : 
    // * groupId : org.anvil
    // * name : acme
    // * project namespace : org.anvil.acme
    // * project path : <root project dir>/src/main/<java|kotlin>/org/anvil/acme
    
    encoding "UTF-8"
    // Managed dependencies
    // These depdenencies are deeply integrated : maven plugin, version management, ...
    libs { 
        jackson {
            enabled false 
            version "x.y.z" // Handled by kriollo 
            core { // add jackson core library
                - "databind" 
            }
        }
        picocli {
            enabled false
            version "x.y.z" // Handled by Kriollo 
        }
        feign {
            enabled false
            version "x.y.z" // Handled by Kriollo 
        }
        jte {
            enabled false
            groupId "gg.jte"
            artifactId "jte"
            version "x.y.z" // Handled by Kriollo 
            sourceDirectory "src/main/jte"
            contentType "Html"
        }
    }
    // Non-managed dependencies
    // This allow adding any dependency into the project
    dependencies {
        - {
            groupId "__GROUP_ID__" // required
            artifactId "__ARTIFACT_ID__" // required
            version "__VERSION__" // required
        }
    }
    // Allow running commands in the project
    customBuildSteps {
        - { 
            id "__ID__" // required, should be unique among build steps
            step "__STEP__" // requirer, step of the lifecycle (ie.: maven phase)
            command "__COMMAND__" // required, the bash command to run
        }
    }
}

// CLI project type support
cli {
    enabled false
    // Controls the running bash script
    script {
        enabled false
        fileName "run.sh" // the script filename
        targetDirectory "./build" // the script directory
        beforeRun: "" // some bash code to run before executing the Jar file
        includeDebug false // include the remote debug instruction
    }
}

// Jetbrains IDE support
jetbrains {
   enabled false 
}

// Java project support
// ⚠ Cannot be used along Kotlin
java {
    enabled false
    version "x.y.z" // handled by Kriollo
}

// Kotlin project support
// ⚠ Cannot be used along Java
kotlin {
    enabled false
    version "x.y.z" // handled by Kriollo
}

// Nix support
nix {
    enabled false
    // Add more packages into the nix configuration file
    customPackages {
        - "foo"
        - "bar"
    }
}

// Git support
git {
    enabled false
    // Add custom ignore instructions to the .gitignore
    ignore {
        - "foo"
        - "bar"
    }
}

// Controls project automated scripts
scripts {
    enabled false
    dir "scripts" // scripts directory
    // Control the build script
    build {
        enabled false
        // Add custom steps to the build script
        customSteps {
            - {
                name "__NAME__" // required, name of the step, shown in the logs
                command "__COMMAND__" // required, bash command to run
            }
        }
    }
    // Control the test script
    tests {
       enabled false 
    }
}

// Maven support 
maven {
    enabled false
    // Activate more Maven Repositories
    repositories {
        jitpack false // add jitpack to available repository
    }
}

// Test support
tests {
    enabled false
    // Configure JUnit/AssertJ
    junit {
        junitVersion "x.y.z" // handled by Kriollo
        assertJVersion "x.y.z" // handled by Kriollo
    }
    // Configure coverage 
    coverage {
        jacocoVersion "x.y.z" // handled by Kriollo
        exclusions { // add exclusions to the coverage check
            - "foo"
            - "bar"
        }
        minimumBranchCoverage "0.5" // check threshold for branches
        minimumInstructionCoverage "0.8" // check threshold for instructions
        maximumMissedClasses "0.5" // null to disable, check threshold for classes
        reportPhase "prepare-package" // maven phase for report generation
    }
}

// Generated components
components { 
    // Kriollo components 
    // The Kriollo project uses Kriollo itself to generate the generators
    kriollo { 
        - {
            path: "__PATH__" // required, path with slashes, relative to the project path 
            name: "__NAME__" // required, to generate some class names
            tag: "__TAG__" // required, to group components together
            activationKey "__ACTIVATION_KEY__" // path to its "enabled" configuration key
            // Declared generators
            // Generators are created into the provided path
            generators {
                - {
                    name "__GENERATOR_NAME__" // required, the generator class name
                }
            }
            // Declared extensions 
            provideExtensions {
                - {
                    name "__EXTENSION_NAME__" // required, the extension interface name
                    provideType "__TYPE__" // required, the extension return type
                }
                // ⚠ Note on the provideType
                // You must add the namespace if the class is not in the same package
                // Or if the class is not in the standard library
            }
            // Extensions implemented by this component
            extensions {
                - {
                    name "__EXTENSION_IMPL__" // required, the class name
                }
            }
        }
    }
}
```