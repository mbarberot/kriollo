# Maven Build System

- Status: Accepted
- Date: 2026-04-24

## Context

We need a build tool to compile, test, and package Kriollo. The choice affects
contributor experience, CI/CD integration, and ecosystem availability.

## Decision

**Use Maven** as the build system.

Rationale:
1. **Convention over configuration** - Standard project layout, predictable phases
2. **Ubiquity** - Most familiar to JVM developers
3. **Plugins** - Rich ecosystem for packaging, assembly, reporting
4. **IDE support** - Excellent integration in IntelliJ, VS Code, Eclipse
5. **CI/CD** - Pre-built integration with GitLab, GitHub, Jenkins

## Alternatives

- **Gradle**: Powerful and flexible, but conventions are hidden behind opaque DSL, making debugging and understanding behavior difficult
- **Ant**: Lightweight and flexible, but old and lacks dependency management and standard conventions
- **Kotlin Script**: Gradle Kotlin DSL offers native Kotlin support, but newer and less familiar to most contributors

## Consequences

- Contributors need Maven knowledge
- XML-based configuration (POM)
- Standard Maven lifecycle: compile, test, package, install
